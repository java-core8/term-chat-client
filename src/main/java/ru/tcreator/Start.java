package ru.tcreator;

import ru.tcreator.logger.Log;
import ru.tcreator.serv.Server;

import java.io.IOException;
import java.util.logging.Level;

public class Start {
    public static void main(String[] args) {
        try {
            Log.toLog(Start.class, Level.INFO, "Старт программы");
            Server server = new Server();
            server.run();
        } catch (IOException e) {
            Log.logTrow(Start.class, "main", e);
        }

    }
}
