package Frame;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Сергей on 23.09.2017.
 */
public class Add   {
    private static Stage primaryStage=new Stage();

    public static void close(){
        primaryStage.close();
    }

    public Add() {
        Parent root= null;

        try {
            root = FXMLLoader.load(getClass().getResource("add.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Добавить пользователя");
        Scene scene=new Scene(root,800,200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
