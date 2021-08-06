package ru.tcreator.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.tcreator.entities.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JSONParserTest {
    @Test
    @DisplayName("Тест парсера на конвертирование JSON в строку")
    @Tag("JSON")
    void testJsonParsingToString() {
        Pattern pattern = Pattern.compile("testMsg");
        Message testMessage = new Message("test", "testMsg");
        String jsonString = JSON.toJson(testMessage);
        Matcher matcher = pattern.matcher(jsonString);
        assertTrue(matcher.find());
    }
    @Test
    @DisplayName("Тест парсера JSON на выброс исключения")
    @Tag("JSON")
    void testJsonParsingToStringAndThrowing() {
        Message messageMock = mock(Message.class);
        assertThrows( UnsupportedOperationException.class, () -> JSON.toJson(messageMock));
    }

    @Test
    @DisplayName("Тест парсера JSON, без исключений ")
    @Tag("JSON")
    void testJsonParsingToStringAndNotThrowing() {
        assertDoesNotThrow(() -> JSON.toJson(
                new Message("test", "test")
        ));
    }
}
