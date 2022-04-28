package com.example.app;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageResult;

public interface AmazonSQSSender {
    void send(String message) throws Exception;
}
