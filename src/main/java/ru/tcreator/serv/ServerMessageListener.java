package ru.tcreator.serv;

import ru.tcreator.interfaces.MessageEntityies;
import ru.tcreator.parser.JSON;

import java.io.IOException;
import java.net.Socket;

public class ServerMessageListener extends ClientHandlerAbstract implements Runnable {
    /**
     * Конструктор. Инициализирует стримы in и out
     *
     * @param clSocket сокет клиента {@link Socket}
     * @throws IOException
     */
    protected ServerMessageListener(Socket clSocket) throws IOException {
        super(clSocket);
    }


    @Override
    public void run() {
        try {
            while (ConnectServer.isConnection()) {
                MessageEntityies msgByServer = JSON.fromJsonMessage(readIn());
                if(msgByServer != null) {
                    System.out.println(msgByServer);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
