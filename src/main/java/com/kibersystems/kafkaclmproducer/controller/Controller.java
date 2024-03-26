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

    private static final String ACTION_1 = "kafkaPreppareMessage";  // Compliant

    @Autowired
    public Controller(ProducerLayer producerLayer) {
        this.producerLayer = producerLayer;
    }

    @GetMapping("/about")
    public String displayHome() {
        return "index";
    }

    @GetMapping("/")
    public String getPrepare(Model model) {
        KafkaPrepareMessage prepareMessage = new KafkaPrepareMessage(1, "test", "test-key", "test-message", 2);
        model.addAttribute(ACTION_1, prepareMessage);
        return "base";
    }

    // handler method to handle user registration form submission request
    @PostMapping("/")
    public String submitForm(Model model,
                             @ModelAttribute("kafkaPreppareMessage") KafkaPrepareMessage kafkaPrepareMessage) {
        model.addAttribute(ACTION_1, kafkaPrepareMessage);
        logger.info("New object KafkaPrepareMessage={}", kafkaPrepareMessage);
        producerLayer.setThreadPool(kafkaPrepareMessage.getCountThreads());
        producerLayer.sendSimpleMessage(kafkaPrepareMessage);
        return "base";
    }

    @PostMapping("/sended")
    public String sendMessage(Model model,
                              @ModelAttribute("kafkaPreppareMessage") KafkaPrepareMessage kafkaPrepareMessage) {
        model.addAttribute(ACTION_1, kafkaPrepareMessage);
        logger.info("New object KafkaPrepareMessage={}", kafkaPrepareMessage);
        producerLayer.sendSimpleMessage(kafkaPrepareMessage);
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
