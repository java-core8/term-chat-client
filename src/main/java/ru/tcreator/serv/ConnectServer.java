package ru.tcreator.serv;

/**
 * Класс маркер. Есть подключение или нет.
 *
 */

abstract class ConnectServer {
    static volatile boolean connection = Boolean.TRUE;

    public static void setDisconnect() {
        connection = Boolean.FALSE;
    }

    public static boolean isConnection() {
        return connection;
    }
}
