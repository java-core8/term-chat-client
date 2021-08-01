package ru.tcreator.serv;
import ru.tcreator.entities.Nickname;
import ru.tcreator.enums.Paths;
import ru.tcreator.logger.Log;
import ru.tcreator.settings.Settings;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;


public class Server {

    public void run() throws IOException {
        Settings settings = new Settings(Paths.SETTINGS.getPath());
        final String HOST = settings.getProperties("HOST");
        final int PORT = Integer.parseInt(settings.getProperties("PORT"));

        boolean nickname = Nickname.getInstance().getNickInLine();
        if(nickname) {
            try {

                Socket clientSocket = new Socket(HOST, PORT);
                //лог
                Log.logger.log(Level.INFO, "Получен сокет клиента " + clientSocket);
                ServerMessageListener serverMessageListener = new ServerMessageListener(clientSocket);
                ClientHandler clientHandler = new ClientHandler(clientSocket);
//                ServerConnectListener serverConnectListener = new ServerConnectListener(clientSocket);
                //TODO неработающая возможность прослушки подключения
                // запрос перебивает сообщения
                // serverConnectListener.setAddress(address);
                Thread listenerThread = new Thread(serverMessageListener);
                Thread clientHandlerThread = new Thread(clientHandler);

                listenerThread.start();
                clientHandlerThread.start();
            } catch (IOException e) {
                Log.logger.throwing(Server.class.getName(), "run", e);
            }
        }
    }


}
