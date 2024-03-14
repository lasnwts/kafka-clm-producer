package com.kibersystems.kafkaclmproducer.service;


import com.kibersystems.kafkaclmproducer.configure.Configure;
import com.kibersystems.kafkaclmproducer.model.KafkaPrepareMessage;
import com.kibersystems.kafkaclmproducer.utils.Supports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerLayer {
    private final Executors executors;
    private final Configure configure;
    private final Supports supports;

    @Autowired
    public ProducerLayer(Executors executors, Configure configure, Supports supports) {
        this.executors = executors;
        this.configure = configure;
        this.supports = supports;
    }

    /**
     * Отправка сообщений в Кафка
     *
     * @param prepareMessage - объект сообщения
     */
    public void sendSimpleMessage(KafkaPrepareMessage prepareMessage) {
        if (prepareMessage != null && prepareMessage.getCountMessage() > 0 && prepareMessage.getTopicName() != null && !prepareMessage.getTopicName().isEmpty()) {
            for (int i = 0; i < prepareMessage.getCountMessage(); i++) {
                executors.getTask(prepareMessage);
            }
        }
    }

    /**
     * Получение количества установленных потоков
     *
     * @return - число потоков
     */
    public int getThreadPool() {
        return configure.getServicePoolSize();
    }


    /**
     * Установка максимального числа потоков
     *
     * @param pool - число потоков
     */
    public boolean setThreadPool(int pool) {
        if (pool == 0) {
            pool = 1;
        } else if (pool > configure.getServicePoolSizeMax()) {
            pool = configure.getServicePoolSizeMax(); //Ограничение максимального числа потоков.
        }
        return executors.setThreadPool(pool);
    }

    /**
     * Получение количества повторов
     *
     * @return
     */
    public int getRepeatCount() {
        return configure.getRepeatCont();
    }

    /**
     * Установка числа повторов
     *
     * @param count
     * @return
     */
    public int setRepeatCount(int count) {
        configure.setRepeatCont(count);
        return getRepeatCount();
    }

    /**
     * Получаем тело сообщения
     *
     * @return
     */
    public String getMessageBody() {
        return supports.getWrapNull(configure.getMessageBody());
    }

    /**
     * Устанавливаем тело сообщения
     *
     * @param body
     * @return
     */
    public String setMessageBody(String body) {
        configure.setMessageBody(body);
        return getMessageBody();
    }

    /**
     * Ключ сообщения
     *
     * @return
     */
    public String getKey() {
        return supports.getWrapNull(configure.getKey());
    }

    /**
     * Установка ключа сообщения
     *
     * @param key
     * @return
     */
    public String setKey(String key) {
        configure.setKey(key);
        return getKey();
    }

    /**
     * Имя топика
     *
     * @return
     */
    public String getTopicName() {
        return supports.getWrapNull(configure.getTopicName());
    }

    /**
     * Получаем имя топика
     *
     * @param topicName
     * @return
     */
    public String setTopicName(String topicName) {
        configure.setTopicName(topicName);
        return getTopicName();
    }

    /**
     * Отправка сообщения
     */
    public void sendPrepareMessage() {
        sendSimpleMessage(new KafkaPrepareMessage(supports.getWrapInt(configure.getRepeatCont()), configure.getTopicName(), configure.getKey(), supports.getWrapNull(configure.getMessageBody())));
    }

    /**
     * Посмотреть подготовленное сообщение
     * @return - Готовое сообщение, здесь вся информация кроме, числа потоков
     */
    public KafkaPrepareMessage getPrepared(){
        return new KafkaPrepareMessage(supports.getWrapInt(configure.getRepeatCont()), configure.getTopicName(), configure.getKey(), supports.getWrapNull(configure.getMessageBody()));
    }


}
