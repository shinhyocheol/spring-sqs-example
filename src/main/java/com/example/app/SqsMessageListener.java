package com.example.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class SqsMessageListener {

    @SqsListener(value = "example-queue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void listen(@Payload String message,
                       @Headers Map<String, Object> headers) {

        log.info("message : " + message);
        log.info("{}", headers);
    }


}
