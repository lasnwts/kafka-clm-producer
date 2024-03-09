package com.kibersystems.kafkaclmproducer.service;


import com.kibersystems.kafkaclmproducer.configure.Configure;
import com.kibersystems.kafkaclmproducer.model.KafkaPrepareMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerLayer {
    private final Executors executors;
    private final Configure configure;

    @Autowired
    public ProducerLayer(Executors executors, Configure configure) {
        this.executors = executors;
        this.configure = configure;
    }

    /**
     * Отправка сообщений в Кафка
     *
     * @param prepareMessage - объект сообщения
     */
    public void sendSimpleMessage(KafkaPrepareMessage prepareMessage) {
        if (prepareMessage != null && prepareMessage.getCountMessage() > 0) {
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
     * Обертка над флагом случайного выбора
     */
    public boolean getRandomizeFlag(){
        return configure.isRandomizeLoad();
    }

    public boolean setRandomaizeFlag(boolean flag){
        configure.setRandomizeLoad(flag);
        return getRandomizeFlag();
    }



}
