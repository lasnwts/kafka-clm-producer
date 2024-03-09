package com.kibersystems.kafkaclmproducer.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class Configure {

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
}

