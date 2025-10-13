package no.hvl.PollApp.messaging;

import no.hvl.PollApp.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.amqp.support.AmqpHeaders;

@Component
public class VoteListener {
    // Name of the queue you declared in your RabbitConfig
    @RabbitListener(queues = RabbitConfig.VOTE_QUEUE)
    public void receiveVote(VoteEvent evt, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey) {
        String pollId = evt.getPollId();
        String optionId = evt.getVoteOptionId();
        String voterId = evt.getVoterId();

        // Now: fetch your Poll entity by pollId, find the VoteOption, update DB or Redis
        System.out.printf("Received vote event: poll=%s option=%s voter=%s (routingKey=%s)%n",
                pollId, optionId, voterId, routingKey);

        // TODO: your domain logic here
    }
}
