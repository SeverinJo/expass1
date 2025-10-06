package no.hvl.PollApp;

import java.util.Map;

public class ValkeyApplication {
    public static void main(String[] args) {
        PollCache cache = new PollCache();
        String pollId = "03ebcb7b-bd69-440b-924e-f5b7d664af7b";

        cache.vote(pollId, "Yes, yammy!");
        cache.vote(pollId, "Yes, yammy!");
        cache.vote(pollId, "Mamma mia, nooooo!");
        cache.vote(pollId, "I do not really care ...");

        // First fetch → likely Redis HIT
        Map<String, String> r1 = cache.getPollResults(pollId);
        System.out.println(r1);

        // Second fetch → Local cache HIT
        Map<String, String> r2 = cache.getPollResults(pollId);
        System.out.println(r2);

        // Vote more
        cache.vote(pollId, "Yes, yammy!");
        cache.vote(pollId, "I do not really care ...");

        // Fetch again
        Map<String, String> r3 = cache.getPollResults(pollId);
        System.out.println(r3);
    }
}
