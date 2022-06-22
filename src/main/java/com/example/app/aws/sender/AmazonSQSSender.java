package com.example.app.aws.sender;

import com.amazonaws.services.sqs.model.SendMessageResult;

public interface AmazonSQSSender {
    SendMessageResult sendMessage(String message) throws Exception;
}
