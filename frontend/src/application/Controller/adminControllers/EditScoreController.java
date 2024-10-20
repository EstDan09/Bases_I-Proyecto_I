package application.Controller.adminControllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import DB.EventsDB;
import DB.TeamsDB;
import application.Model.Event;
import application.Model.Medal;
import application.Model.Score;
import application.Model.Teams;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditScoreController {

    private int id;

    // Method to accept a parameter id
    public void setId(int id) throws SQLException {
        this.id = id;
        if (this.id != 0) {
            title.setText("Edit Score");
            Score editScore = DB.ScoreDB.getScoreById(this.id);
            selectEvent.setValue(editScore.getEvent());
            editTeam.setValue(editScore.getTeam());
            scoreField.setText(editScore.getScore());
            medalScore.setValue(editScore.getMedal());
        }
    }

    // FXML linked UI components
    @FXML
    private Hyperlink loginBtn;

    @FXML
    private Label title;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<Teams> editTeam;

    @FXML
    private TextField scoreField;

    @FXML
    private ComboBox<Medal> medalScore;

    @FXML
    private Label errorMsg;

    @FXML
    private ComboBox<Event> selectEvent;

    // Initialization method that runs after FXML elements are loaded
    @FXML
    public void initialize() throws SQLException {
        // Populate ComboBox with events
        ObservableList<Event> eventList = EventsDB.getEventsList();
        selectEvent.setItems(eventList);
        
        ObservableList<Medal> medalList = DB.MedalDB.getMedalList();
        medalScore.setItems(medalList);
        
        // Clear error message
        errorMsg.setVisible(false);
    }

    // Action method for Save button
    @FXML
    public void saveScore(ActionEvent event) throws SQLException, IOException {
        // Validate input fields
        if (editTeam.getValue() == null || scoreField.getText().isEmpty()) {
            errorMsg.setText("Please fill in all required fields");
            errorMsg.setVisible(true);
            return;
        }        

        // Create Score object
        Score currentScore = new Score(0,
                selectEvent.getValue(),
                editTeam.getValue(),
                scoreField.getText(),
                medalScore.getValue()
        );

        // Save into DB
        if (this.id == 0) {
            DB.ScoreDB.addScore(currentScore);
        } else {
            currentScore.setId(this.id);
            DB.ScoreDB.updateScore(currentScore);
        }

        Parent root = FXMLLoader.load(getClass().getResource("../../Views/AdminView.fxml"));
        String title = "Admin Dashboard";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../../application.css").toExternalForm());
        stage.setTitle("Olympics | " + title);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    // Action method for Login hyperlink
    @FXML
    public void switchToLoginScene() {
        // Logic to switch to login scene (this typically involves loading a new FXML file and changing the stage)
        System.out.println("Switching to login scene");
    }
    
    @FXML
    public void chooseEvent() throws SQLException {
    	Event selectedEvent = selectEvent.getSelectionModel().getSelectedItem();
    	
    	ObservableList<Teams> teamList = TeamsDB.getTeamListByEvent(selectedEvent.getId());
        editTeam.setItems(teamList);
        
    }
}
