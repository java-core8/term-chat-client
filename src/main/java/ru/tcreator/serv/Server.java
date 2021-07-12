package ru.tcreator.serv;

import java.io.*;
import java.net.Socket;

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

                Thread tr = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            while (true) {
                                if (!client.isClosed()) {
                                    String readLine = in.readLine();
                                    System.out.println(readLine);
                                }

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
                tr.start();
                while (client.isConnected()) {
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
