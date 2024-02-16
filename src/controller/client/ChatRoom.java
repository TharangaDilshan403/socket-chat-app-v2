package controller.client;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import controller.Data;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.ConnectionUtil;

import java.io.*;
import java.net.Socket;
import static controller.LogIn.username;

public class ChatRoom extends Thread{
    public TextField msgField;
    public JFXButton btnSend;
    public Label userNametxt;
    public JFXTextArea txtChatRoom;
    public Pane emogiPane;

    Socket socket = null;
    PrintWriter writer;
    public FileChooser chooser;
    public File path;

   public void initialize() throws IOException {
       userNametxt.setText(username);
       System.out.println("userName is : " + Data.username);
       socket = new Socket(ConnectionUtil.host, ConnectionUtil.port);
       txtChatRoom.appendText("Connect. \n");
       writer = new PrintWriter(socket.getOutputStream());
       TaskReadThread task = new TaskReadThread(socket, this);
       Thread thread = new Thread(task);
       thread.start();

       emogiPane.setVisible(false);
   }

    public void sendOnAction(MouseEvent actionEvent) throws IOException {
        PrintWriter printWriter= new PrintWriter(socket.getOutputStream());
        printWriter.println(Data.username + " : " + msgField.getText());
        printWriter.flush();
        msgField.clear();
    }

    public void emogiOnAction(MouseEvent mouseEvent) {
        if (!emogiPane.isVisible()) {
            emogiPane.setVisible(true);
        } else {
            emogiPane.setVisible(false);
        }
    }
//    public void run() {
//        try {
//            while (true) {
//                String massage = bufferedReader.readLine();
//                String[] tokens = massage.split(" ");
//                String command = tokens[0];
//
//                StringBuilder clientMassage = new StringBuilder();
//                for (int i = 1; i < tokens.length; i++) {
//                    clientMassage.append(tokens[i] + " ");
//                }
//
//                String[] massageAr = massage.split(" ");
//                String string = "";
//                for (int i = 0; i < massageAr.length - 1; i++) {
//                    string += massageAr[i + 1] + " ";
//                }
//
//                Text text = new Text(string);
//                String fChar = "";
//
//                if (string.length() > 3) {
//                    fChar = string.substring(0, 3);
//                }
//
//                if (fChar.equalsIgnoreCase("img")) {
//                    string = string.substring(3, string.length() - 1);
//
//                    File file = new File(string);
//                    Image image = new Image(file.toURI().toString());
//
//                    ImageView imageView = new ImageView(image);
//
//                    imageView.setFitWidth(150);
//                    imageView.setFitHeight(200);
//
//                    HBox hBox = new HBox(10);
//                    hBox.setAlignment(Pos.BOTTOM_RIGHT);
//
//                    if (!command.equalsIgnoreCase(clientlbl.getText())) {
//                        vBox.setAlignment(Pos.TOP_LEFT);
//                        hBox.setAlignment(Pos.CENTER_LEFT);
//
//                        Text text1 = new Text("  " + command + " :");
//                        hBox.getChildren().add(text1);
//                        hBox.getChildren().add(imageView);
//                    } else {
//                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
//                        hBox.getChildren().add(imageView);
//                        Text text1 = new Text(" ");
//                        hBox.getChildren().add(text1);
//                    }
//
//                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));
//
//                } else {
//                    TextFlow tempTextFlow = new TextFlow();
//
//                    if (!command.equalsIgnoreCase(userNametxt.getText() + ":")) {
//                        Text name = new Text(command + " ");
//                        name.getStyleClass().add("name");
//                        tempTextFlow.getChildren().add(name);
//                    }
//
//                    tempTextFlow.getChildren().add(text);
//                    tempTextFlow.setMaxWidth(200);
//
//                    TextFlow textFlow = new TextFlow(tempTextFlow);
//                    HBox hBox = new HBox(12);
//
//                    if (!command.equalsIgnoreCase(userNametxt.getText() + ":")) {
//                        vBox.setAlignment(Pos.TOP_LEFT);
//                        hBox.setAlignment(Pos.CENTER_LEFT);
//                        hBox.getChildren().add(textFlow);
//                    }
////                    else {
////                        Text text1 = new Text(clientMassage+"");
////                        TextFlow textFlow1 = new TextFlow(text1);
////                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
////                        hBox.getChildren().add(textFlow1);
////                    }
//                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void emoji1OnAction(MouseEvent mouseEvent) {
        msgField.appendText("\uD83D\uDE03");
    }

    public void imageOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        chooser = new FileChooser();
        chooser.setTitle("Open Image");
        this.path = chooser.showOpenDialog(stage);
        writer.println(userNametxt.getText() +" : "+ path.getPath());
        writer.flush();

    }

    public void emoji2OnAction(MouseEvent mouseEvent) {
        msgField.appendText("\uD83E\uDD17");
    }

    public void emoji3OnAction(MouseEvent mouseEvent) {
        msgField.appendText("❤️❤️");
    }

    public void emoji4OnAction(MouseEvent mouseEvent) {
        msgField.appendText("\uD83D\uDE32");
    }

    public void emoji5OnAction(MouseEvent mouseEvent) {
        msgField.appendText("\uD83D\uDE02");
    }

    public void emoji6OnAction(MouseEvent mouseEvent) {
        msgField.appendText("\uD83D\uDE25");
    }

    public void emoji7OnAction(MouseEvent mouseEvent) {
        msgField.appendText("\uD83D\uDE2B");
    }

    public void emoji8OnAction(MouseEvent mouseEvent) {
        msgField.appendText("\uD83D\uDE24");
    }
}
