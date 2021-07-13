package ru.tcreator.serv;

import ru.tcreator.interfaces.MessageEntityies;
import ru.tcreator.parser.JSON;

import java.io.*;
import java.net.Socket;

public class ClientHandlerAbstract {
    protected final BufferedReader in;
    protected final Socket socket;
    protected final BufferedWriter out;
    protected final BufferedReader terminalReader;
    protected boolean disconnected = Boolean.FALSE;

    /**
     * Конструктор. Инициализирует стримы in и out и ридер терминала
     * @param clSocket сокет клиента {@link Socket}
     * @throws IOException
     */
    protected ClientHandlerAbstract(Socket clSocket) throws IOException {
        this.socket = clSocket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.terminalReader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Закрывает соединения стримов и сокета
     * @throws IOException
     */
    protected void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }


    /**
     * Отсылает сообщение для текущего соединения
     * @param msg объект сообщения {@link MessageEntityies}
     * @throws IOException
     */
    protected void writeOut(String msg) throws IOException {
        out.write(msg + "\n");
        out.flush();
    }

    /**
     * Считывает из входящего потока соединения
     * @return Message
     * @throws IOException
     */
    protected String readIn() throws IOException {
        return in.readLine();
    }

    /**
     * поднимает флаг disconected в true. Метка прерывания потоков сервера
     */
    protected void setDisconnected() {
        this.disconnected = Boolean.TRUE;
    }
}
