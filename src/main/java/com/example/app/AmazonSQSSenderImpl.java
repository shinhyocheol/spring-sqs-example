package com.example.app;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class AmazonSQSSenderImpl implements AmazonSQSSender{

   private QueueMessagingTemplate queueMessagingTemplate;

   @Autowired
   public AmazonSQSSenderImpl(AmazonSQS amazonSQS) {
       this.queueMessagingTemplate = new QueueMessagingTemplate((AmazonSQSAsync) amazonSQS);
   }


    @Override
    public void send(String message) throws Exception {
        Message<String> payload = MessageBuilder
                .withPayload(message)
                .build();

        queueMessagingTemplate.send("example-queue", payload);
    }

}
