package com.kibersystems.kafkaclmproducer.model;

public class EventKafka {
    public int countMessage; //Кол-во запланированных отправок сообщений
    private int countThread; //Кол-во потоков отправки
    private int countMessageSended; //Кол-во уже отправленныхь сообщений

    public EventKafka() {
        //
    }

    public EventKafka(int countMessage, int countThread, int countMessageSended) {
        this.countMessage = countMessage;
        this.countThread = countThread;
        this.countMessageSended = countMessageSended;
    }

    public int getCountMessage() {
        return countMessage;
    }

    public void setCountMessage(int countMessage) {
        this.countMessage = countMessage;
    }

    public int getCountThread() {
        return countThread;
    }

    public void setCountThread(int countThread) {
        this.countThread = countThread;
    }

    public int getCountMessageSended() {
        return countMessageSended;
    }

    public void setCountMessageSended(int countMessageSended) {
        this.countMessageSended = countMessageSended;
    }

    @Override
    public String toString() {
        return "EventKafka{" +
                "countMessage=" + countMessage +
                ", countThread=" + countThread +
                ", countMessageSended=" + countMessageSended +
                '}';
    }
}
