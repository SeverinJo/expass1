package no.hvl.PollApp.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import no.hvl.PollApp.config.RabbitConfig;

@Component
public class VoteListener {
    @RabbitListener(queues = RabbitConfig.VOTE_QUEUE)
    public void handleVote(VoteEvent evt, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey) {
        String pollId = evt.getPollId();  // or parse from routingKey
        String optionId = evt.getVoteOptionId();
        // fetch Poll, find option, persist Vote, etc.
    }
}
