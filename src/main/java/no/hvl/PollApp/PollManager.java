package no.hvl.PollApp;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class PollManager {
    private final Map<Long, User> users = new HashMap<>();
    private final Map<Long, Poll> polls = new HashMap<>();
    private final Map<Long, VoteOption> options = new HashMap<>();
    private final Map<Long, Vote> votes = new HashMap<>();
    private final AtomicLong ids = new AtomicLong(1);

    // ---- Users
    public User createUser(User u) {
        u.setId(ids.getAndIncrement());
        users.put(u.getId(), u);
        return u;
    }

    public Optional<User> getUser(long id) {
        return Optional.ofNullable(users.get(id));
    }

    public Collection<User> listUsers() {
        return users.values();
    }

    public Optional<User> updateUser(long id, User patch) {
        User u = users.get(id);
        if (u == null) return Optional.empty();
        u.setUsername(patch.getUsername());
        u.setEmail(patch.getEmail());
        return Optional.of(u);
    }

    public boolean deleteUser(long id) {
        return users.remove(id) != null;
    }

    // ---- Polls
    public Poll createPoll(Poll p, long creatorId) {
        p.setId(ids.getAndIncrement());
        User creator = users.get(creatorId);
        p.setCreator(creator);
        if (creator != null) creator.getPolls().add(p);
        polls.put(p.getId(), p);
        return p;
    }

    public Optional<Poll> getPoll(long id) {
        return Optional.ofNullable(polls.get(id));
    }

    public Collection<Poll> listPolls() {
        return polls.values();
    }

    public Optional<Poll> updatePoll(long id, Poll patch) {
        Poll p = polls.get(id);
        if (p == null) return Optional.empty();
        p.setQuestion(patch.getQuestion());
        p.setPublishedAt(patch.getPublishedAt());
        p.setValidUntil(patch.getValidUntil());
        return Optional.of(p);
    }

    public boolean deletePoll(long id) {
        Poll p = polls.remove(id);
        if (p == null) return false;
        if (p.getCreatedBy() != null) p.getCreatedBy().getPolls().remove(p);
        // remove options + related votes
        for (VoteOption o : new ArrayList<>(p.getOptions())) {
            deleteOption(o.getId());
        }
        votes.values().removeIf(v -> v.getOption() != null && v.getOption().getPoll() != null && v.getOption().getPoll().getId() == id);
        return true;
    }

    // ---- Options
    public VoteOption addOption(long pollId, VoteOption o) {
        Poll p = polls.get(pollId);
        o.setId(ids.getAndIncrement());
        o.setPoll(p);
        options.put(o.getId(), o);
        if (p != null) p.getOptions().add(o);
        return o;
    }

    public Optional<VoteOption> getOption(long id) {
        return Optional.ofNullable(options.get(id));
    }

    public boolean deleteOption(long id) {
        VoteOption o = options.remove(id);
        if (o == null) return false;
        if (o.getPoll() != null) o.getPoll().getOptions().remove(o);
        votes.values().removeIf(v -> v.getOption() != null && v.getOption().getId() == id);
        return true;
    }

    // ---- Votes
    public Vote castVote(long userId, long optionId, Vote v) {
        v.setId(ids.getAndIncrement());
        v.setVoter(users.get(userId));
        v.setOption(options.get(optionId));
        votes.put(v.getId(), v);
        if (v.getVoter() != null) v.getVoter().getVotes().add(v);
        return v;
    }

    public Optional<Vote> getVote(long id) {
        return Optional.ofNullable(votes.get(id));
    }

    public Collection<Vote> listVotes() {
        return votes.values();
    }

    public Optional<Vote> updateVote(long id, long newOptionId) {
        Vote v = votes.get(id);
        if (v == null) return Optional.empty();
        v.setOption(options.get(newOptionId));
        return Optional.of(v);
    }

    public boolean deleteVote(long id) {
        return votes.remove(id) != null;
    }
}