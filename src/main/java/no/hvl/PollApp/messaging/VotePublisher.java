package no.hvl.PollApp.messaging;

import no.hvl.PollApp.event.VoteEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotePublisher {
    public static final String EXCHANGE = "polls.exchange";  // same as your old EXCHANGE_NAME

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishVote(String pollId, String voteOptionId, String voterId) {
        String routingKey = "poll.vote." + pollId;
        VoteEvent evt = new VoteEvent(pollId, voteOptionId, voterId);
        rabbitTemplate.convertAndSend(EXCHANGE, routingKey, evt);
    }
}
