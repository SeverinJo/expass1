package no.hvl.PollApp;

import io.valkey.UnifiedJedis;
import java.util.Map;

public class PollCache {
    private final UnifiedJedis jedis;

    public PollCache() {
        this.jedis = new UnifiedJedis("redis://127.0.0.1:6379");
    }

    public Map<String, String> getPollResults(String pollId) {
        String key = "poll:" + pollId;
        Map<String, String> cached = jedis.hgetAll(key);
        if (!cached.isEmpty()) {
            System.out.println("Cache hit for poll " + pollId);
            return cached;
        }

        System.out.println("Cache miss for poll " + pollId + " — computing fake DB results...");

        Map<String, String> dbResult = Map.of(
                "title", "Pineapple on Pizza?",
                "option:yes_yammy", "269",
                "option:mamma_mia_nooooo", "268",
                "option:i_do_not_really_care", "42"
        );

        jedis.hset(key, dbResult);
        jedis.expire(key, 300);
        return dbResult;
    }

    public void invalidatePoll(String pollId) {
        jedis.del("poll:" + pollId);
    }
}
