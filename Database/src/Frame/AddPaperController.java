package Frame;

import database.Papers;
import database.Participant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddPaperController {
    @FXML
    private TextField id;
    @FXML
    private TextField title;
    @FXML
    private TextField type;
    @FXML
    private TextField section;
    @FXML
    private TextField sdate;


    @FXML
    private Label errorLabel;

    public void onAddClick(ActionEvent actionEvent) throws Exception {
        Papers papers=null;
        try {
            papers=new Papers(Integer.valueOf(id.getText()),title.getText(),type.getText(),section.getText(),sdate.getText());
            Frame.getDatabase().addPapers(papers);
            Add.close();

        } catch (Exception e) {
            errorLabel.setText("Произошла ошибка. Закройте окно и попробуйте заново.");
            e.printStackTrace();

        }

    }
}


