package ru.tcreator.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringParserTestClass {
    
    @Test
    @DisplayName("Парсинг строки, без выброса исключения")
    @Tag("StringParser")
    void testStringParserToNotThrowing() {
        String testMsg = "///Это,,.. сообщение содержит \\ $/sdsd цу в /eee=sad себе много всего незаконного";
        assertDoesNotThrow(() -> new StringParser(testMsg));
    }

    @Test
    @DisplayName("Парсинг строки, определение команды в сообщении, только команда")
    @Tag("StringParser")
    void testStringParserToGetCommand() {
        String testMsg = "/exit";
        String equalText = "exit";
        StringParser stringParser = new StringParser(testMsg);
        assertEquals(equalText, stringParser.cleanCommand);
    }

    @Test
    @DisplayName("Парсинг строки, определение команды в сообщении, команда с текстом")
    @Tag("StringParser")
    void testStringParserToGetCommandWithText() {
        String testMsg = "/exit тестовая строка";
        String equalCommand = "exit";
        String equalMsg  = "тестовая строка";
        StringParser stringParser = new StringParser(testMsg);
        assertEquals(equalCommand, stringParser.getCommand());
        assertEquals(equalMsg, stringParser.getMessage());
    }

    @Test
    @DisplayName("Парсинг строки, определение команды в сообщении, команда с параметром")
    @Tag("StringParser")
    void testStringParserToGetCommandWithParameter() {
        String testMsg = "/exit=4545 тестовая строка";
        String equalCommand = "exit";
        String equalParameter = "4545";
        String equalMsg  = "тестовая строка";
        StringParser stringParser = new StringParser(testMsg);
        assertEquals(equalCommand, stringParser.getCommand());
        assertEquals(equalParameter, stringParser.getParameter());
        assertEquals(equalMsg, stringParser.getMessage());
    }
}
