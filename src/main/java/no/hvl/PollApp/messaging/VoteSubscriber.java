package no.hvl.PollApp.messaging;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

public class VoteSubscriber {
    private static final String EXCHANGE_NAME = "polls.exchange";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "poll.vote.*");

        System.out.println(" [*] Waiting for vote events...");

        DeliverCallback callback = (consumerTag, delivery) -> {
            String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
            String routingKey = delivery.getEnvelope().getRoutingKey();
            System.out.printf(" [x] Received '%s' (routingKey: %s)%n", msg, routingKey);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        channel.basicConsume(queueName, false, callback, consumerTag -> {});
    }
}
