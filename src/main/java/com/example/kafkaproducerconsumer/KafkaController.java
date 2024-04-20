package com.example.kafkaproducerconsumer;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/kafka")
public class KafkaController {

    private KafkaTemplate<String, String> kafkaTemplate;
    private Gson jsonConverter;

    @Autowired
    public KafkaController(KafkaTemplate<String, String> kafkaTemplate, Gson jsonCOonverter, Gson jsonConverter) {
        this.kafkaTemplate = kafkaTemplate;
        this.jsonConverter = jsonConverter;
    }

    @PostMapping
    public void post(@RequestBody MyModel myModel){
        kafkaTemplate.send("myTopic", jsonConverter.toJson(myModel));
    }

    @PostMapping("v2")
    public void post2(@RequestBody MoreModel moreModel){
        kafkaTemplate.send("myTopic2", jsonConverter.toJson(moreModel));
    }

    @KafkaListener(topics =  "myTopic")
    public void getFromKafka(String myModelAsStr){
        System.out.println(myModelAsStr);
        MyModel myModel = jsonConverter.fromJson(myModelAsStr, MyModel.class);
        System.out.println(myModel.getField1() + " : " + myModel.getField2());
    }

    @KafkaListener(topics =  "myTopic2")
    public void getFromKafka2(String moreModelAsStr){
        System.out.println(moreModelAsStr);
        MoreModel moreModel = jsonConverter.fromJson(moreModelAsStr, MoreModel.class);
        System.out.println(moreModel.getTitle() + " : " + moreModel.getDescription());
    }


}
