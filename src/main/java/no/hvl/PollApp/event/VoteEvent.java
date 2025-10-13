package no.hvl.PollApp.event;

import java.io.Serializable;

public class VoteEvent implements Serializable {
    private String pollId;
    private String voteOptionId;
    private String voterId;

    public VoteEvent() {}

    public VoteEvent(String pollId, String voteOptionId, String voterId) {
        this.pollId = pollId;
        this.voteOptionId = voteOptionId;
        this.voterId = voterId;
    }

    public String getPollId() {
        return pollId;
    }
    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    public String getVoteOptionId() {
        return voteOptionId;
    }
    public void setVoteOptionId(String voteOptionId) {
        this.voteOptionId = voteOptionId;
    }

    public String getVoterId() {
        return voterId;
    }
    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }
}
