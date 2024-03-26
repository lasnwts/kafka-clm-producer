package com.kibersystems.kafkaclmproducer.controller;

import com.kibersystems.kafkaclmproducer.dto.ServiceResponse;
import com.kibersystems.kafkaclmproducer.model.EventKafka;
import com.kibersystems.kafkaclmproducer.model.KafkaPrepareMessage;
import com.kibersystems.kafkaclmproducer.service.ProducerLayer;
import com.kibersystems.kafkaclmproducer.utils.Supports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rest2Controller {

    private final Logger logger = LoggerFactory.getLogger(Rest2Controller.class);
    private final ProducerLayer producerLayer;
    private final Supports supports;

    @Autowired
    public Rest2Controller(ProducerLayer producerLayer, Supports supports) {
        this.producerLayer = producerLayer;
        this.supports = supports;
    }


    /**
     * POST
     */
    @PostMapping("/rest-home")
    public ResponseEntity<Object> restHome(@RequestBody KafkaPrepareMessage kafkaPrepareMessage) {
        logger.info("New object KafkaPrepareMessage={}", kafkaPrepareMessage);
        producerLayer.setThreadPool(kafkaPrepareMessage.getCountThreads());
        producerLayer.sendSimpleMessage(kafkaPrepareMessage);
        EventKafka eventKafka = new EventKafka(kafkaPrepareMessage.getCountMessage(), kafkaPrepareMessage.getCountThreads(), producerLayer.getMessageSendCount());
        ServiceResponse<EventKafka> response = new ServiceResponse<EventKafka>("success", eventKafka);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }



}
