package ru.tcreator;

import ru.tcreator.serv.Server;

import java.io.IOException;

public class Start {
    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
