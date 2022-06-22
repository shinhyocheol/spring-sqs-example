package com.example.app;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AmazonSQSSenderImpl implements AmazonSQSSender{

    private final ObjectMapper objectMapper;
    private final AmazonSQS amazonSQS;
    private final AmazonSQSProperties amazonSQSProperties;


    @Override
    public SendMessageResult sendMessage(String message) throws JsonProcessingException {
        SendMessageRequest sendMessageRequest = new SendMessageRequest(
                amazonSQSProperties.getUrl(),
                objectMapper.writeValueAsString(message)
        );

        return amazonSQS.sendMessage(sendMessageRequest);
    }
}
