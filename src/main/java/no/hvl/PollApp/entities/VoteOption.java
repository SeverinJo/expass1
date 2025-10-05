package no.hvl.PollApp.entities;

import jakarta.persistence.*;
import org.springframework.data.redis.core.RedisHash;

@Entity
@RedisHash
@Table(name="vote_options")
public class VoteOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String caption;
    private int presentationOrder;
    @ManyToOne
    private Poll poll;

    public VoteOption() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(int presentationOrder) {
        this.presentationOrder = presentationOrder;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}
