package org.superbiz.moviefun.movies;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RabbitMessageController {

    private RabbitTemplate rabbitTemplate;
    private String rabbitmqname;


    public RabbitMessageController(RabbitTemplate rabbitTemplate, @Value("${rabbitmq.queue}") String queueNam) {
        this.rabbitmqname = queueNam;
        this.rabbitTemplate = rabbitTemplate;

    }

    @PostMapping("/rabbit")
    public Map<String, String> publicmessage() {
        rabbitTemplate.convertAndSend(rabbitmqname, "This is the message we are trying to send to Rabbit");
        Map<String, String> aux = new HashMap<>();
        aux.put("response", "This is unrelated JSOn response");
        return aux;

    }


}
