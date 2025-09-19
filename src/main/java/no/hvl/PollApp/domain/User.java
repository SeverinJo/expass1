package no.hvl.PollApp.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    @OneToMany
    private Set<Poll> created = new LinkedHashSet<>();
    @OneToMany
    private List<Vote> votes = new ArrayList<>();

    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.created = new LinkedHashSet<>();
    }

    public Poll createPoll(String question) {
        Poll poll = new Poll(question);
        poll.setCreatedBy(this);
        poll.setPublishedAt(Instant.now());
        poll.setValidUntil(Instant.parse("2025-12-31T23:59:59.00Z"));
        this.created.add(poll);
        return poll;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Poll> getCreated() {
        return created;
    }

    public void setCreated(Set<Poll> created) {
        this.created = created;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
