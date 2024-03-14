package com.kibersystems.kafkaclmproducer.utils;

import org.springframework.stereotype.Component;

@Component
public class Supports {

    /**
     * Обертка над значением null в строке
     *
     * @param line - переданная строка
     * @return - строка после проверки на NULL.
     */
    public String getWrapNull(String line) {
        if (line == null) {
            return "";
        } else {
            return line.trim();
        }
    }

    /**
     * Обертка над количеством сообщений
     *
     * @param i - установленное число повторов
     * @return - если число 0 -> вернем 1.
     */
    public int getWrapInt(int i) {
        if (i <= 0) {
            return 1;
        } else {
            return i;
        }
    }

}
