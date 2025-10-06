package no.hvl.PollApp;

import io.valkey.UnifiedJedis;
import java.util.*;

public class PollCache {
    private final UnifiedJedis jedis;

    // Optional: in-process cache (just for one run). Use if you want local speed.
    private final Map<String, Map<String, String>> localCache = new HashMap<>();

    public PollCache() {
        this.jedis = new UnifiedJedis("redis://127.0.0.1:6379");
    }

    public Map<String, String> getPollResults(String pollId) {
        // Check local in-process cache first
        if (localCache.containsKey(pollId)) {
            System.out.println("[Local Cache HIT] " + pollId);
            return localCache.get(pollId);
        }

        // Otherwise, check Redis
        String redisKey = "poll:" + pollId;
        Map<String, String> redisHash = jedis.hgetAll(redisKey);
        if (redisHash != null && !redisHash.isEmpty()) {
            System.out.println("[Redis HIT] " + redisKey);
            localCache.put(pollId, redisHash);
            return redisHash;
        }

        System.out.println("[Miss] no data found for poll:" + pollId);
        return Map.of();
    }

    public long vote(String pollId, String caption) {
        String redisKey = "poll:" + pollId;
        String field = "option:" + caption;
        long newCount = jedis.hincrBy(redisKey, field, 1);
        localCache.remove(pollId);
        return newCount;
    }
}
