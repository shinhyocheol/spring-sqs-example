package com.example.app.api;

import com.example.app.aws.sender.AmazonSQSSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SqsController {

    private final AmazonSQSSender sqsMessageSender;

    @PostMapping(value = "/api/v1/send")
    public ResponseEntity<String> send() throws Exception {

        sqsMessageSender.sendMessage("message");
        
        return ResponseEntity
                .ok()
                .body("SUCCESS");
    }

}
