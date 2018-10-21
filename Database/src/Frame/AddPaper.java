package Frame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPaper {

    private static Stage primaryStage = new Stage();

    public static void close() {
        primaryStage.close();
    }

    public AddPaper() {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("addPaper.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Добавить пользователя");
        Scene scene = new Scene(root, 800, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
