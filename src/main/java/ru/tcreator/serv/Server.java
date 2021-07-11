package ru.tcreator.serv;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public void run() {
        int port = 10156;
        try {
            Socket client = new Socket("localhost", 10156);
            try(
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    PrintWriter terminal = new PrintWriter(new OutputStreamWriter(System.out));
                    BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in))
                    ) {

                while (client.isConnected()) {
                    String readLine = in.readLine();
                    terminal.println(readLine);

                    String read = terminalReader.readLine();
                    out.write(read + "\n");
                    out.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
