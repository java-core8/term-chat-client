package ru.tcreator.serv;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ServerConnectListener extends ClientHandlerAbstract implements Runnable {
    protected InetSocketAddress address;

    protected ServerConnectListener(Socket clSocket) throws IOException {
        super(clSocket);
    }

    @Override
    public void run() {
        //TODO переписать проверку на соединение с сокетом сервера. Если работает in.read он может перебить стрим
        // на получение сообщения. Это тупо. Проверку скорее всего проще сделать в потоке слушателя сообщений от сервера
        // но всё равно странная тема
//        while (ConnectServer.isConnection()) {
//            try {
//                TimeUnit.MILLISECONDS.sleep(2000);
//                if (in.read() == -1) {
//                    ConnectServer.setDisconnect();
//                }
//            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void setAddress(InetSocketAddress address) {
        this.address = address;
    }
}
