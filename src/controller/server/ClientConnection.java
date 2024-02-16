package controller.server;

import java.io.*;
import java.net.Socket;

public class ClientConnection implements Runnable{
    private final Socket accept;
    private final Server server;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String user;

    public ClientConnection(Socket socket, Server server, String user) {
        this.accept = socket;
        this.server = server;
        this.user = user;
    }

    @Override
    public void run() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(accept.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while (true) {
                String readLine = bufferedReader.readLine();
                System.out.println("Line : " + readLine);
                server.broadcast(readLine);
                server.txtServerRoom.appendText(readLine + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                accept.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}

    public void sendMessage(String message) {
        try {
            PrintWriter printWriter = new PrintWriter(accept.getOutputStream());
            printWriter.println(message);
            System.out.println("He hee : "+message);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

