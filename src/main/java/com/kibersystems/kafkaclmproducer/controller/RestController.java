package com.kibersystems.kafkaclmproducer.controller;

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


/**
 * Класс для тестирования канала отправки почтовых сооющений
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1")
@Tag(name = "Контроллер для проверки почтовой подсистемы", description = "Проверка отправки почты")
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
     * Тест тправки почты
     */
    @PostMapping(value = "/")
    @Operation(summary = "Отправка сообщения в топик Кафка")
    public ResponseEntity<String> sendToTopic(KafkaPrepareMessage prepareMessage) {
        logger.info("log:{}", prepareMessage);
        producerLayer.sendSimpleMessage(prepareMessage);
        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }

    /**
     * Тест отправки почты
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
            if (producerLayer.setThreadPool(pool)){
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

}
