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

}
