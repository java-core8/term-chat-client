package ru.tcreator.serv;

import ru.tcreator.entities.Message;
import ru.tcreator.parser.JSON;
import ru.tcreator.parser.JSONMessageLog;

import java.io.IOException;
import java.net.Socket;

public class ServerMessageListener extends ClientHandlerAbstract implements Runnable {
    /**
     * Конструктор. Инициализирует стримы in и out
     *
     * @param clSocket сокет клиента {@link Socket}
     */
    protected ServerMessageListener(Socket clSocket) throws IOException {
        super(clSocket);
    }


    @Override
    public void run() {
        try {
            while (ConnectServer.isConnection()) {
                String serverString = readIn();
                Message msgByServer = JSON.fromJsonMessage(serverString);
                JSONMessageLog.addMessageFile(msgByServer);
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
