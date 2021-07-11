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
                    PrintWriter terminalWriter = new PrintWriter(new OutputStreamWriter(System.out));
                    BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
                    ) {
                String stringByServer = in.readLine();
                if(stringByServer != null) {
                    terminalWriter.write(stringByServer);
                    if(stringByServer.contains("никнейм")) {
                        String readLine = terminalReader.readLine().trim();
                        out.write(readLine + "\n");
                        out.flush();
                    }
                }
                while (client.isConnected()) {
                    String readLine = terminalReader.readLine().trim();
                    out.write(readLine + "\n");
                    out.flush();

                    String stringByServ = in.readLine();
                    if(stringByServ != null) {
                        terminalWriter.write(stringByServ);
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
