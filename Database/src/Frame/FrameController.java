package Frame;

import database.Employee;
import database.Papers;
import database.Participant;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.awt.print.Paper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Сергей on 21.09.2017.
 */
public class FrameController {
    @FXML
    private Label ErrorLabel;
    private ObservableList<Participant> participants= FXCollections.observableArrayList();
    private ObservableList<Papers> papers=FXCollections.observableArrayList();
    @FXML
    private Label IDLabel;
    @FXML
    private Button IDButton;
    @FXML
    private TextField IDTextField;
    @FXML
    private TextArea OutputTextArea;
    @FXML
    private TableView<Participant> OutputTable;
    @FXML
    private TableColumn<Participant,Integer> Id;
    @FXML
    private TableColumn<Participant,String> Fname;
    @FXML
    private TableColumn<Participant,String> AcademicDegree;
    @FXML
    private TableColumn<Participant,String> PlaceOfWork;
    @FXML
    private TableColumn<Participant,String> Position;
    @FXML
    private TableColumn<Participant,String> CitizenShip;
    @FXML
    private TableColumn<Participant,String> Bdate;
    @FXML
    private TableView<Papers> papersTable;
    @FXML
    private TableColumn<Papers,Integer> Id1;
    @FXML
    private TableColumn<Papers,String> Title;
    @FXML
    private TableColumn<Papers,String> Type;
    @FXML
    private TableColumn<Papers,String> Sectionname;
    @FXML
    private TableColumn<Papers,String> Sdate;

    //private Employee employee;
    private boolean status=true;
    public void onClick(ActionEvent actionEvent) {
        try {
            if(!status) {
                Participant delete = OutputTable.getItems().get(0);
                participants.remove(delete);
            }
            Participant participant;
            ErrorLabel.setText("");
            participant=Frame.getDatabase().getInfo(Integer.valueOf(IDTextField.getText()));
            participants.add(participant);
            OutputTable.setItems(participants);
            status=false;
        } catch (Exception e) {
            status=true;
            ErrorLabel.setText("Произошла ошибка");
        }
    }
    @FXML
    public void initialize(){
        Id.setCellValueFactory(new PropertyValueFactory<Participant,Integer>("id"));
        Fname.setCellValueFactory(new PropertyValueFactory<Participant,String>("fname"));
        AcademicDegree.setCellValueFactory(new PropertyValueFactory<Participant,String>("academicdegree"));
        PlaceOfWork.setCellValueFactory(new PropertyValueFactory<Participant,String>("placeOfWork"));
        Position.setCellValueFactory(new PropertyValueFactory<Participant,String>("position"));
        CitizenShip.setCellValueFactory(new PropertyValueFactory<Participant,String>("citizenship"));
        Bdate.setCellValueFactory(new PropertyValueFactory<Participant,String>("bdate"));
        Id1.setCellValueFactory(new PropertyValueFactory<Papers,Integer>("id"));
        Title.setCellValueFactory(new PropertyValueFactory<Papers,String>("title"));
        Type.setCellValueFactory(new PropertyValueFactory<Papers,String>("type"));
        Sectionname.setCellValueFactory(new PropertyValueFactory<Papers,String >("sectionname"));
        Sdate.setCellValueFactory(new PropertyValueFactory<Papers,String>("sdate"));

    }

    public void onAddClick(ActionEvent actionEvent) throws Exception {
       /* Frame.getDatabase().add(new Employee(1111,"1","1",1234,"12.05.1996",2314,211,10));
        Employee employee;
        employee=Frame.getDatabase().getInfo(1111);
        employees.add(employee);
        OutputTable.setItems(employees);*/
      new Add();
    }

    public void onDeleteClick(ActionEvent actionEvent) throws SQLException {
        Frame.getDatabase().delete(Integer.valueOf(IDTextField.getText()));
    }

    public void onClick1(ActionEvent actionEvent) {
        try {
            if(!status) {
                List<Papers> delete = papersTable.getItems();
                papers.remove(delete);
            }
            List<Papers> paper=new ArrayList<>();
            ErrorLabel.setText("");
            paper=Frame.getDatabase().getPapers(Integer.valueOf(IDTextField.getText()));
            papers.addAll(paper);
            papersTable.setItems(papers);
            status=false;
        } catch (Exception e) {
            status=true;
            ErrorLabel.setText("Произошла ошибка");
        }
    }

    public void onDeleteClick1(ActionEvent actionEvent) throws SQLException {
        Frame.getDatabase().deletePapers(Integer.valueOf(IDTextField.getText()));
    }

    public void onAddClick1(ActionEvent actionEvent) {
        new AddPaper();
    }

    public void onShowClick(ActionEvent actionEvent) {
        try {
            if(!status) {
                Papers delete = papersTable.getItems().get(0);
                papers.remove(delete);
            }
            List<Papers> paper=new ArrayList<>();
            ErrorLabel.setText("");
            paper=Frame.getDatabase().show();
            papers.addAll(paper);
            papersTable.setItems(papers);
            status=false;
        } catch (Exception e) {
            status=true;
            ErrorLabel.setText("Произошла ошибка");
        }
    }

    public void onshow(ActionEvent actionEvent) {
        try {
            if(!status) {
                List<Participant> delete = OutputTable.getItems();
                participants.remove(delete);
            }
            List<Participant> participant=new ArrayList<>();
            ErrorLabel.setText("");
            participant=Frame.getDatabase().showParticipants();
            participants.addAll(participant);
            OutputTable.setItems(participants);
            status=false;
        } catch (Exception e) {
            status=true;
            ErrorLabel.setText("Произошла ошибка");
        }
    }
}
