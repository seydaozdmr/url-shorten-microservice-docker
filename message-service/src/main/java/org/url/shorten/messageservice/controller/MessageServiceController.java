package org.url.shorten.messageservice.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.url.shorten.messageservice.service.CounterService.QUEUE;

@RestController
public class MessageServiceController {
    private final RabbitTemplate rabbitTemplate;

    public MessageServiceController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/increment")
    public ResponseEntity incCounter(@RequestParam("shortUrl") String shortUrl){
        rabbitTemplate.convertAndSend(QUEUE,shortUrl);
        return ResponseEntity.ok().build();
    }
}
