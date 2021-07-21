package ru.tcreator.serv;

import ru.tcreator.entities.Message;
import ru.tcreator.entities.MessageBuilder;
import ru.tcreator.entities.Nickname;
import ru.tcreator.logger.Log;
import ru.tcreator.parser.JSON;
import ru.tcreator.parser.StringParser;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

public class ClientHandler extends ClientHandlerAbstract implements Runnable {
    public ClientHandler(Socket clSocket) throws IOException {
        super(clSocket);
    }

    @Override
    public void run() {
        try {
            //Отправка никнейма
            Log.toLog(ClientHandler.class, Level.INFO, "Ожидание никнейма");
            Nickname nicknameClass = Nickname.getInstance();
            String nickname = nicknameClass.getNickname();
            Message message = new MessageBuilder()
                    .setFrom(nickname)
                    .setMsg(nickname)
                    .buildMessage();
            writeOut(JSON.toJson(message));
            Log.toLog(ClientHandler.class, Level.INFO, "Никнейм отправлен");

            while (ConnectServer.isConnection()) {
                Log.toLog(ClientHandler.class, Level.INFO, "ожидание строки пользователя");

                String readLineTerminal = terminalReader.readLine();
                StringParser parser = new StringParser(readLineTerminal);

                Message toServer = new MessageBuilder()
                        .setFrom(nickname)
                        .setMsg(parser.getMessage())
                        .setCommand(parser.getCommand())
                        .setParameter(parser.getParameter())
                        .buildMessage();
                writeOut(JSON.toJson(toServer));
                Log.toLog(ClientHandler.class, Level.INFO, "сообщение отправлено");
            }
        } catch (IOException e) {
            Log.logTrow(ClientHandler.class,  "run", e);
        }
    }
}
