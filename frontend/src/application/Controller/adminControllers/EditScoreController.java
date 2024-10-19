package application.Controller.adminControllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import application.Model.Event;
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
            /*Score editScore = DB.ScoreDB.getScoreById(this.id);
            selectEvent.setValue(editScore.getEditEvent());
            editTeam.setText(editScore.getEditTeam());
            scoreField.setText(editScore.getScores());
            medalsField.setText(editScore.getMedals());*/
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
    private TextField editTeam;

    @FXML
    private TextField scoreField;

    @FXML
    private TextField medalsField;

    @FXML
    private Label errorMsg;

    @FXML
    private ComboBox<Event> selectEvent;

    // Initialization method that runs after FXML elements are loaded
    @FXML
    public void initialize() throws SQLException {
        // Populate ComboBox with events
        /*ObservableList<Event> eventList = DB.ScoreDB.getScoreList();
        selectEvent.setItems(eventList);
*/
        // Clear error message
        errorMsg.setVisible(false);
    }

    // Action method for Save button
    @FXML
    public void saveScore(ActionEvent event) throws SQLException, IOException {
        // Validate input fields
        if (editTeam.getText().isEmpty() || scoreField.getText().isEmpty() || medalsField.getText().isEmpty()) {
            errorMsg.setText("Please fill in all required fields");
            errorMsg.setVisible(true);
            return;
        }

        String teamName = editTeam.getText();
        String scores = scoreField.getText();
        String medals = medalsField.getText();

        // Create Score object
       /* Score currentScore = new Score(
                selectEvent.getValue(),
                teamName,
                scores,
                medals
        );

        // Save into DB
        if (this.id == 0) {
            DB.ScoreDB.addScore(currentScore);
        } else {
            currentScore.setId(this.id);
            DB.ScoreDB.updateScore(currentScore);
        }*/

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
}
