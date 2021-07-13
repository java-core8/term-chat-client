package ru.tcreator.parser;

public class StringParser {
    protected String cleanCommand;
    protected String message;
    protected boolean haveCommand = Boolean.FALSE;

    public StringParser(String msg) {
        String[] parseMessage = parseMessageString(msg);
        if(parseMessage[0] != null) {
            parseMessage[0] = parseMessage[0].replace("/", "").trim();
            if(parseMessage[0].length() > 0) {
                haveCommand = Boolean.TRUE;
                cleanCommand = parseMessage[0];
            }
        }
        message = parseMessage[1];
    }

    public boolean haveCommand() {
        return haveCommand;
    }


    /**
     * Парсит строку сообщения на само сообщение и подстроки команд, если такие есть
     * @param source {@link String} строка поиска
     * @return {@link String[]} массив из строки команды и строки сообщения. [String = null, String]
     */
    protected static String[] parseMessageString(String source) {
        StringBuilder command = new StringBuilder();
        StringBuilder msgLine = new StringBuilder();
        boolean commandIsFind = Boolean.FALSE;// находим только первое вхождение

        for (int i = 0; i < source.length(); i++) {
            char currentChar = source.charAt(i);
            if(currentChar == '\\') {
                continue;
            }
            if(currentChar == '/') { // проверяем на символ начала команды
                if(!commandIsFind) {
                    while (i < source.length()) { // перебираем символы до пробела
                        char tmpChar = source.charAt(i);
                        if(tmpChar != ' ' && tmpChar != '\n') {

                            command.append(tmpChar);
                            i++;
                        } else {
                            break;
                        }
                    }
                    commandIsFind = Boolean.TRUE;
                    continue;
                }

            }
            msgLine.append(source.charAt(i));
        }
        return new String[] {
                command.toString(), // если есть команда
                msgLine.toString() // если команда встроена, сообщение не будет его содержать
        };
    }

    public String getCommand() {
        return cleanCommand;
    }

    public String getMessage() {
        return message;
    }
}
