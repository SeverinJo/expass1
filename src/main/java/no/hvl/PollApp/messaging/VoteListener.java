package no.hvl.PollApp.messaging;  // or component, whichever you prefer under scan

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.amqp.support.AmqpHeaders;
import no.hvl.PollApp.config.RabbitConfig;
import no.hvl.PollApp.event.VoteEvent;

@Component
public class VoteListener {
    @RabbitListener(queues = RabbitConfig.VOTE_QUEUE)
    public void handleVote(VoteEvent evt, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey) {
        String pollId = evt.getPollId();
        String optionId = evt.getVoteOptionId();
        String voterId = evt.getVoterId();

        System.out.printf("Received vote event: poll=%s option=%s voter=%s routingKey=%s%n",
                pollId, optionId, voterId, routingKey);

        // TODO: your domain logic (persist vote, update poll, etc.)
    }
}
