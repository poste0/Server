package Frame;

import database.Employee;
import database.Participant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Сергей on 23.09.2017.
 */
public class AddController {
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField degreeTextField;
    @FXML
    private TextField placeTextField;
    @FXML
    private TextField positionTextField;
    @FXML
    private TextField citizenTextField;
    @FXML
    private TextField bdateTextField;

    @FXML
    private Label errorLabel;

    public void onAddClick(ActionEvent actionEvent) throws Exception {
        Participant participant=null;
            try {
                participant=new Participant(Integer.valueOf(idTextField.getText()),nameTextField.getText(),degreeTextField.getText(),placeTextField.getText(),
                        positionTextField.getText(),citizenTextField.getText(),bdateTextField.getText());
                Frame.getDatabase().add(participant);
                Add.close();

            } catch (Exception e) {
                errorLabel.setText("Произошла ошибка. Закройте окно и попробуйте заново.");
                e.printStackTrace();

        }

    }
}
