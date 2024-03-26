package com.kibersystems.kafkaclmproducer.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Класс Подготовки сообщений
 */

@Schema(description = "Сообщение в Кафка")
public class KafkaPrepareMessage {
    @Schema(example = "10", description = "Количество отправляемых сообщений")
    private int countMessage; //Количество сообщений
    @Schema(example = "topic-name", description = "Имя топика")
    private String topicName; //Имя топика
    @Schema(example = "key-100", description = "Ключ сообщения")
    private String key; //Значение ключа в сообщении
    @Schema(example = "-body-", description = "Тело сообщения")
    private String message; //Строка с сообщением
    @Schema(example = "10", description = "Количество потоков сообщений")
    private int countThreads; //Количество потоков
    public KafkaPrepareMessage(int countMessage, String topicName, String key, String message, int countThreads) {
        this.countMessage = countMessage;
        this.topicName = topicName;
        this.key = key;
        this.message = message;
        this.countThreads = countThreads;
    }

    public int getCountMessage() {
        return countMessage;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    public void setCountMessage(int countMessage) {
        this.countMessage = countMessage;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCountThreads() {
        return countThreads;
    }

    public void setCountThreads(int countThreads) {
        this.countThreads = countThreads;
    }
    @Override
    public String toString() {
        return "KafkaPrepareMessage{" +
                "countMessage=" + countMessage +
                ", topicName='" + topicName + '\'' +
                ", key='" + key + '\'' +
                ", message='" + message + '\'' +
                ", countThreads=" + countThreads +
                '}';
    }
}
