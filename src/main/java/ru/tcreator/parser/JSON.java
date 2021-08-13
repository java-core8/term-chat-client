package ru.tcreator.parser;

import com.google.gson.Gson;
import ru.tcreator.entities.Message;

import java.lang.reflect.Type;

/**
 * Класс для сериализации и десериализации объектов сообщения
 */
public class JSON {
    protected static Gson gson = new Gson();

    /**
     * Сериализует объект в Json строку
     * @param msgObject {@link Message}
     * @return строка json
     */
    static public String toJson(Message msgObject) {
        return gson.toJson(msgObject);
    }


    /**
     * Парсит из JSON в Объект сообщения
     * @param json {@link String}
     * @return {@link Message} объект сообщения
     */
    static public Message fromJsonMessage(String json) {
        return gson.fromJson(json, Message.class);
    }
}
