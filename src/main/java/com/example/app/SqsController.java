package com.example.app;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SqsController {

    private final AmazonSQSSender sqsMessageSender;

    @GetMapping(value = "/api/v1/send")
    public ResponseEntity<String> send() throws Exception {

        sqsMessageSender.send("this is queue");
        
        return ResponseEntity
                .ok()
                .body("SUCCESS");
    }

}
