package ru.tcreator.serv;

import ru.tcreator.entities.Message;
import ru.tcreator.logger.Log;
import ru.tcreator.parser.JSON;
import ru.tcreator.parser.JSONMessageLog;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

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
                if(msgByServer != null) {
                    JSONMessageLog.addMessageFile(msgByServer);
                    //вывод в чат
                    System.out.println(msgByServer);
                } else {
                    Log.logger.log(Level.WARNING, "Обрыв соединения с сервером");
                    // закрытие всех потоков
                    ConnectServer.setDisconnect();
                    close();
                }
            }
        } catch (IOException e) {
            Log.logger.throwing(ServerMessageListener.class.getName(), "run", e);
        }
    }
}
