package com.example.kafkaproducerconsumer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/kafka")
public class KafkaController {

    private KafkaTemplate<String, MyModel> kafkaTemplate;

    @Autowired
    public KafkaController(KafkaTemplate<String, MyModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void post(@RequestBody MyModel myModel){
        kafkaTemplate.send("myTopic", myModel);
        System.out.println(myModel);
    }



}
