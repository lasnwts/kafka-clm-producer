package com.kibersystems.kafkaclmproducer.controller;

import com.kibersystems.kafkaclmproducer.model.KafkaPrepareMessage;
import com.kibersystems.kafkaclmproducer.service.ProducerLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@org.springframework.stereotype.Controller
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final ProducerLayer producerLayer;
    @Autowired
    public Controller(ProducerLayer producerLayer) {
        this.producerLayer = producerLayer;
    }

    @GetMapping("/about")
    public String displayHome() {
        return "index";
    }

    @GetMapping("/message")
    public String getPrepare(Model model){
        KafkaPrepareMessage prepareMessage = new KafkaPrepareMessage(10,"test", "test-key", "test-message-1", 2);
        model.addAttribute("kafkaPreppareMessage", prepareMessage);
        return "home2";
    }

    // handler method to handle user registration form submission request
    @PostMapping("/message")
    public String submitForm(Model model,
                             @ModelAttribute("kafkaPreppareMessage") KafkaPrepareMessage kafkaPrepareMessage){
        model.addAttribute("kafkaPreppareMessage", kafkaPrepareMessage);
        logger.info("New object KafkaPrepareMessage={}", kafkaPrepareMessage);
        producerLayer.sendSimpleMessage(kafkaPrepareMessage);
        return "home2";
    }

    @PostMapping("/sended")
    public String sendMessage(Model model,
                              @ModelAttribute("kafkaPreppareMessage") KafkaPrepareMessage kafkaPrepareMessage){
        model.addAttribute("kafkaPreppareMessage", kafkaPrepareMessage);
        logger.info("New object KafkaPrepareMessage={}", kafkaPrepareMessage);
        producerLayer.sendSimpleMessage(kafkaPrepareMessage);
        return "index";
    }
}
