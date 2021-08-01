package ru.tcreator.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.tcreator.entities.Message;
import ru.tcreator.enums.Paths;
import ru.tcreator.logger.Log;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class JSONMessageLog extends JSON {
    static public void addMessageFile(Message msg) {
        String path = Paths.JSON_LOG.getPath();
        List<Message> msgArr = readMessageSource();
        msgArr.add(msg);
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(msgArr, writer);
        } catch (IOException e) {
            Log.logger.throwing(JSONMessageLog.class.getName(), "addMessageFile", e);
        }
    }

    static private List<Message> readMessageSource() {
        String path = Paths.JSON_LOG.getPath();
        Gson gson = new Gson();
        List<Message> messageArray = null;
        try (Reader reader = new FileReader(path)) {
            Type messageTypeList = new TypeToken<List<Message>>(){}.getType();
            messageArray = gson.fromJson(reader, messageTypeList);
        } catch (IOException e) {
            Log.logger.throwing(JSONMessageLog.class.getName(), "readMessageSource", e);
        }
        return messageArray;
    }
}
