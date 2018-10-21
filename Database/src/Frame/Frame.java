package Frame;

import database.Employee;
import database.OracleDatabase;
import database.Participant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Сергей on 21.09.2017.
 */
public class Frame extends Application {
    private static OracleDatabase database;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("Frame.fxml"));
        primaryStage.setTitle("JDBC");
        Scene scene=new Scene(root,800,500);
        primaryStage.setScene(scene);
        primaryStage.show();
        database=new OracleDatabase("SSO_6307","12345","1521","localhost");

    }
    protected static Participant getEmployee(int id) throws Exception {
        return database.getInfo(id);
    }
    public static OracleDatabase getDatabase(){return database;}
}
