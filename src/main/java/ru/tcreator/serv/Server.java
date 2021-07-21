package ru.tcreator.serv;

import ru.tcreator.entities.Nickname;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;



public class Server {
    protected InetSocketAddress address = new InetSocketAddress("localhost", 51124);

    public void run() throws IOException {
        boolean nickname = Nickname.getInstance().getNickInLine();
        if(nickname) {
            try {
                Socket clientSocket = new Socket(address.getHostString(), address.getPort());
                ServerMessageListener serverMessageListener = new ServerMessageListener(clientSocket);
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                ServerConnectListener serverConnectListener = new ServerConnectListener(clientSocket);
                serverConnectListener.setAddress(address);
                Thread listenerThread = new Thread(serverMessageListener);
                Thread clientHandlerThread = new Thread(clientHandler);
                Thread serverConnectionListenerThread = new Thread(serverConnectListener);

                listenerThread.start();
                clientHandlerThread.start();
                serverConnectionListenerThread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
