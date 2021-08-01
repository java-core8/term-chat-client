package ru.tcreator.serv;

import ru.tcreator.entities.Message;
import ru.tcreator.entities.MessageBuilder;
import ru.tcreator.entities.Nickname;
import ru.tcreator.logger.Log;
import ru.tcreator.parser.JSON;
import ru.tcreator.parser.JSONMessageLog;
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
            Log.logger.log(Level.INFO, "Ожидание никнейма");
            Nickname nicknameClass = Nickname.getInstance();
            String nickname = nicknameClass.getNickname();
            Message message = new MessageBuilder()
                    .setFrom(nickname)
                    .setMsg(nickname)
                    .buildMessage();
            writeOut(JSON.toJson(message));
            Log.logger.log(Level.INFO, "Никнейм отправлен");

            while (ConnectServer.isConnection()) {

                String readLineTerminal = terminalReader.readLine();
                StringParser parser = new StringParser(readLineTerminal);

                Message toServer = new MessageBuilder()
                        .setFrom(nickname)
                        .setMsg(parser.getMessage())
                        .setCommand(parser.getCommand())
                        .setParameter(parser.getParameter())
                        .buildMessage();
                // отправляем на сервер
                writeOut(JSON.toJson(toServer));
                JSONMessageLog.addMessageFile(toServer);
                Log.logger.log(Level.INFO, "сообщение отправлено");
            }
        } catch (IOException e) {
            Log.logger.throwing(ClientHandler.class.getName(),  "run", e);
        }
    }
}
