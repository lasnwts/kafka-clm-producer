package com.kibersystems.kafkaclmproducer.controller;

import com.kibersystems.kafkaclmproducer.KafkaClmProducerApplication;
import com.kibersystems.kafkaclmproducer.dto.ServiceResponse;
import com.kibersystems.kafkaclmproducer.model.EventKafka;
import com.kibersystems.kafkaclmproducer.model.KafkaPrepareMessage;
import com.kibersystems.kafkaclmproducer.service.ProducerLayer;
import com.kibersystems.kafkaclmproducer.utils.Supports;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Класс для тестирования канала отправки почтовых сооющений
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1")
@Tag(name = "Контроллер для подготовки и отправки сообщений на Кафка", description = "Отправка сообщений")
public class RestController {
    private final Logger logger = LoggerFactory.getLogger(RestController.class);
    private final ProducerLayer producerLayer;
    private final Supports supports;

    @Autowired
    public RestController(ProducerLayer producerLayer, Supports supports) {
        this.producerLayer = producerLayer;
        this.supports = supports;
    }

    /**
     * Отправка сообщения, основной
     */
    @PostMapping(value = "/messages")
    @Operation(summary = "Отправка сообщения в топик Кафка")
    public ResponseEntity<String> sendToTopic(KafkaPrepareMessage prepareMessage) {
        logger.info("log:{}", prepareMessage);
        producerLayer.sendSimpleMessage(prepareMessage);
        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }

    /**
     * Количество потоков, получить
     */
    @GetMapping(value = "/pool")
    @Operation(summary = "Просмотр установленного числа потоков отправки")
    ResponseEntity<String> getThreadPool() {
        try {
            return new ResponseEntity<>("Количество потоков=" + producerLayer.getThreadPool(), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Ошибка при запросе число установленных потоков!", exception);
            return new ResponseEntity<>("Ошибка при запросе число установленных потоков!"
                    + supports.getWrapNull(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/pool/{pool}")
    @Operation(summary = "Установка нового значения числа потоков отправки сообщений")
    ResponseEntity<String> setThreadPool(
            @Parameter(description = "Thread pool queue size", example = "10")
            @PathVariable(value = "pool") int pool) {
        try {
            if (producerLayer.setThreadPool(pool)) {
                return new ResponseEntity<>("Установлено новое значение -количества потоков=" + producerLayer.getThreadPool(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Произошла ошибка при установлении нового числа потоков=" + producerLayer.getThreadPool(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception exception) {
            logger.error("Ошибка при попытке установке нового значения->{} потоков!", pool);
            logger.error("Stack trace", exception);
            return new ResponseEntity<>("Ошибка при попытке установке нового значения!"
                    + supports.getWrapNull(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Получить имя топика
     */
    @GetMapping(value = "/topic")
    @Operation(summary = "Просмотр топика")
    ResponseEntity<String> getTopicName() {
        return new ResponseEntity<>(producerLayer.getTopicName(), HttpStatus.OK);
    }

    /**
     * Установить флаг (случайный или нет порядок отправки)
     */
    @PutMapping(value = "/topic/{topic}")
    @Operation(summary = "Установка имени топика")
    ResponseEntity<String> setTopicName(@Parameter(description = "Установите имя топика", example = "siebel.insurance.experimental.0")
                                        @PathVariable(required = true) String topic) {
        return new ResponseEntity<>(producerLayer.setTopicName(topic), HttpStatus.OK);
    }

    /**
     * Получить имя топика
     */
    @GetMapping(value = "/repeat")
    @Operation(summary = "Просмотр количество сообщений к отправке")
    ResponseEntity<Integer> getRepeatCount() {
        return new ResponseEntity<>(producerLayer.getRepeatCount(), HttpStatus.OK);
    }

    /**
     * Установить флаг (случайный или нет порядок отправки)
     */
    @PutMapping(value = "/repeat/{count}")
    @Operation(summary = "Установка числа сообщений")
    ResponseEntity<Integer> setRepratCount(@Parameter(description = "Установите число сообщений", example = "25")
                                        @PathVariable(required = true) int count) {
        return new ResponseEntity<>(producerLayer.setRepeatCount(count), HttpStatus.OK);
    }

    /**
     * Получить имя топика
     */
    @GetMapping(value = "/message")
    @Operation(summary = "Просмотр тела сообщения")
    ResponseEntity<String> getMessage() {
        return new ResponseEntity<>(producerLayer.getMessageBody(), HttpStatus.OK);
    }

    /**
     * Установить флаг (случайный или нет порядок отправки)
     */
    @PostMapping(value = "/message")
    @Operation(summary = "Ввод тела сообщения")
    ResponseEntity<String> setMessage(@RequestBody(required = true) String messageBody) {
        logger.info("log:{}", messageBody);
        return new ResponseEntity<>(producerLayer.setMessageBody(messageBody), HttpStatus.OK);
    }

    /**
     * Получить ключ
     */
    @GetMapping(value = "/key")
    @Operation(summary = "Просмотр ключа")
    ResponseEntity<String> getKey() {
        return new ResponseEntity<>(producerLayer.getKey(), HttpStatus.OK);
    }

    /**
     * Установка ключа
     */
    @PutMapping(value = "/key/{key}")
    @Operation(summary = "Установка ключа сообщения")
    ResponseEntity<String> setKey(@Parameter(description = "Установите ключ сообщения", example = "key1234567898")
                                        @PathVariable(required = true) String key) {
        return new ResponseEntity<>(producerLayer.setKey(key), HttpStatus.OK);
    }


    /**
     * Запуск ранее подготовленного сообщения
     */
    @GetMapping(value = "/start")
    @Operation(summary = "Запуск отправки сообщения")
    ResponseEntity<String> sendPrepareMessage() {
        producerLayer.sendPrepareMessage();
        return new ResponseEntity<>("Сообщение отправлено", HttpStatus.OK);
    }

    /**
     * Просмотр ранее подготовленного сообщения
     */
    @GetMapping(value = "/prepared")
    @Operation(summary = "Просмотр подготовленного сообщения")
    ResponseEntity<KafkaPrepareMessage> getPrepareMessage() {
        return new ResponseEntity<>(producerLayer.getPrepared(), HttpStatus.OK);
    }

    /**
     * Перезапуск приложения через API
     */
    @GetMapping("/restart")
    public void restart() {
        KafkaClmProducerApplication.restart();
    }

    /**
     * Методы для работы с формой html
     */

    /**
     * GET
     * @return
     */
//    public ResponseEntity<Object>  getFormPrepare(){
//        ServiceResponse<EventKafka> response = new ServiceResponse<>("success", EventKafka);
//    }

}
