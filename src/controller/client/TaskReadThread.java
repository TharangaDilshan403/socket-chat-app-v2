package controller.client;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TaskReadThread implements Runnable{
    Socket socket;
    ChatRoom client;
    BufferedReader reader;

    public TaskReadThread(Socket socket, ChatRoom client) {
        this.socket = socket;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = reader.readLine()) != null) {
                appendMessageToChatRoom(message);
            }
        } catch (IOException e) {
            handleIOException(e);
        } finally {
            closeResources();
        }
    }

    private void appendMessageToChatRoom(String message) {
        Platform.runLater(() -> {
            client.txtChatRoom.appendText(message + "\n");
        });
    }

    private void handleIOException(IOException e) {
        // Handle IOException appropriately
        e.printStackTrace();
    }

    private void closeResources() {
        try {
            if (reader != null) {
                reader.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            // Handle IOException during closing resources
            e.printStackTrace();
        }
    }
}
