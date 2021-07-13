package ru.tcreator.serv;

import ru.tcreator.entityies.Message;
import ru.tcreator.entityies.MessageBuilder;
import ru.tcreator.enums.SendStatus;
import ru.tcreator.parser.JSON;
import ru.tcreator.parser.StringParser;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends ClientHandlerAbstract implements Runnable {
    protected String nickname;

    public ClientHandler(Socket clSocket) throws IOException {
        super(clSocket);
    }

    @Override
    public void run() {
        try {
            // Первая стадия ввод и отправка никнейма
            String nicknameLine = terminalReader.readLine();
            Message message = new MessageBuilder()
                    .setFrom(nicknameLine)
                    .setMsg(nicknameLine)
                    .buildMessage();

            writeOut(JSON.toJson(message));

            while (ConnectServer.isConnection()) {
                String readLineTerminal = terminalReader.readLine();
                //TODO а вот тут нормально написать обработку сообщений
                // хотя просто так в чат всю херню кидать вообще ок. Лол
                StringParser parser = new StringParser(readLineTerminal);
                System.out.println(parser.getCommand());
                System.out.println(parser.getMessage());

                Message toServer = new MessageBuilder()
                        .setFrom(nicknameLine)
                        .setMsg(parser.getMessage())
                        .setCommand(parser.getCommand())
                        .setStatus(SendStatus.GOOD)
                        .buildMessage();

                System.out.println(toServer.getCommand());
                System.out.println(toServer.getMsg());
                writeOut(JSON.toJson(toServer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
