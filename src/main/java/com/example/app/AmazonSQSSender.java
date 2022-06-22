package com.example.app;

import com.amazonaws.services.sqs.model.SendMessageResult;

public interface AmazonSQSSender {
    SendMessageResult sendMessage(String message) throws Exception;
}
