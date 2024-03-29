package com.kibersystems.kafkaclmproducer.configure;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class Configure {

    @Schema(example = "0", description = "Количество уже отправленных сообщений")
    private int sendCount; //Количество отправленных сообщений

    /**
     * service.pool.size=5
     * service.mode=one
     */
    @Value("${service.pool.size:5}")
    private Integer servicePoolSize;
    @Value("${service.pool.max:20}")
    private Integer servicePoolSizeMax;

    /**
     * Количество задач в очереди
     */
    private int threads;

    /**
     * Случайный выбор файла сообщения
     */
    private boolean randomizeLoad;

    /**
     * Имя топика, ключа, сообщение, количество повторов
     */
    private int repeatCont; //Количество сообщений
    private String messageBody; //Тело сообщения
    private String key; //Ключ сообщения
    private String topicName; //Имя топика


    /**
     * Application properties
     */
    @Value("${info.application.name}")
    private String appName;

    @Value("${info.application.description}")
    private String appDescription;

    @Value("${info.application.version}")
    private String appVersion;

    /**
     * Реализация)
     */
    public String getAppName() {
        return appName;
    }
    public String getAppDescription() {
        return appDescription;
    }
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * Кол-во потоков
     */
    public synchronized int getThreads() {
        return threads;
    }
    public synchronized void setThreads(int threads) {
        this.threads = threads;
    }
    public synchronized int getServicePoolSize() {
        return servicePoolSize;
    }
    public synchronized void setServicePoolSize(Integer servicePoolSize) {
        this.servicePoolSize = servicePoolSize;
    }
    public Integer getServicePoolSizeMax() {
        return servicePoolSizeMax;
    }

    public synchronized boolean isRandomizeLoad() {
        return randomizeLoad;
    }

    public synchronized void setRandomizeLoad(boolean randomizeLoad) {
        this.randomizeLoad = randomizeLoad;
    }

    public int getRepeatCont() {
        return repeatCont;
    }

    public void setRepeatCont(int repeatCont) {
        this.repeatCont = repeatCont;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public synchronized int getSendCount() {
        return sendCount;
    }

    public synchronized void setSendCount(int sendCount) {
        this.sendCount = sendCount;
    }
}

