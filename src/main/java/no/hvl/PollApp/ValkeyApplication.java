package no.hvl.PollApp;

import io.valkey.UnifiedJedis;

public class ValkeyApplication {
    public static void main(String[] args) {
        try (UnifiedJedis jedis = new UnifiedJedis("redis://127.0.0.1:6379")) {

            String pollKey = "poll:03ebcb7b-bd69-440b-924e-f5b7d664af7b";

            jedis.hset(pollKey, "title", "Pineapple on Pizza?");
            jedis.hset(pollKey, "option:Yes, yammy!", "269");
            jedis.hset(pollKey, "option:Mamma Mia, nooooo!", "268");
            jedis.hset(pollKey, "option:I do not really care...", "42");

            System.out.println("Initial poll hash:");
            jedis.hgetAll(pollKey).forEach((field, value) ->
                    System.out.println(field + " = " + value)
            );

            jedis.hincrBy(pollKey, "option:Yes, yammy!", 1);

            System.out.println("\nAfter increment:");
            jedis.hgetAll(pollKey).forEach((field, value) ->
                    System.out.println(field + " = " + value)
            );

            // Optional: set TTL if you want cache behavior
            jedis.expire(pollKey, 300); // seconds
        }
    }
}
