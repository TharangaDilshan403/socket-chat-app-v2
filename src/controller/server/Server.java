package controller.server;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Server implements Initializable {
    public TextField msgField;
    public JFXButton btnSend;
    public JFXTextArea txtServerRoom;

    ServerSocket serverSocket;
    List<ClientConnection> connectionList = new ArrayList<>();

    public void sendOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(()->{
            try{
                serverSocket = new ServerSocket(5000);
                System.out.println("Server Started!");
                while (true) {
                    Socket accept = serverSocket.accept();
                    System.out.println("Connect new");
                    ClientConnection connection = new ClientConnection(accept, this,"user");
                    connectionList.add(connection);

                    Thread thread = new Thread(connection);
                    thread.start();

                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();
    }

    public void broadcast(String message) {
        for (ClientConnection clientConnection : this.connectionList) {
            clientConnection.sendMessage(message);
        }
    }
}
