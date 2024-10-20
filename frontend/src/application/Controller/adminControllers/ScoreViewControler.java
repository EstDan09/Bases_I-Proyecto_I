package application.Controller.adminControllers;

import java.io.IOException;
import java.sql.SQLException;

import DB.ScoreDB;
import application.Model.Score;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class ScoreViewControler {
   
    @FXML
    private TableView<Score> scoreTableView;

    @FXML
    private TableColumn<Score, Integer> id;

    @FXML
    private TableColumn<Score, String> event;

    @FXML
    private TableColumn<Score, String> team;

    @FXML
    private TableColumn<Score, String> score;

    @FXML
    private TableColumn<Score, String> medal;
    
    @FXML
    private Button addBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button deleteBtn;
    
    @FXML
    public void initialize() throws SQLException {
        // Configurar columnas con el objeto Score
    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	event.setCellValueFactory(new PropertyValueFactory<>("event"));
    	team.setCellValueFactory(new PropertyValueFactory<>("team"));
    	score.setCellValueFactory(new PropertyValueFactory<>("score"));
    	medal.setCellValueFactory(new PropertyValueFactory<>("medal"));

    	// Populate the table with data from the DB
        ObservableList<Score> scoreList = DB.ScoreDB.getScoreList();

        // Añadir la lista a la tabla
        scoreTableView.setItems(scoreList);
    }

    @FXML
    private void addItem(ActionEvent event) throws IOException {
    	/// Load the FXML
        Parent root = FXMLLoader.load(getClass().getResource("../../Views/Admin/EditScore.fxml"));
        
        // Set the new content in the center of your content panel
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../../application.css").toExternalForm());
        stage.setTitle("Add Team");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void editItem(ActionEvent event) throws IOException, SQLException {
    	// Get the selected Score
    	Score selectedScore = scoreTableView.getSelectionModel().getSelectedItem();
        if (selectedScore != null) {
            // Create FXMLLoader instance
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Views/Admin/EditScore.fxml"));  
            
            // Load the FXML
            Parent root = loader.load();

            // Get the controller of the new scene
            EditScoreController controller = loader.getController();

            // Pass the parameter to the controller's method
            controller.setId(selectedScore.getId());

            // Set the new content in the center of your content panel
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../../application.css").toExternalForm());
            stage.setTitle("Edit Score");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an score to edit.");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteItem(ActionEvent event) throws SQLException {
    	// Get the selected Event
    	Score selectedScore = scoreTableView.getSelectionModel().getSelectedItem();
        if (selectedScore != null) {
            // Add logic for deleting the selected item
            ScoreDB.deleteScore(selectedScore.getId());
            
            scoreTableView.getItems().remove(selectedScore);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Delete Item");
            alert.setHeaderText(null);
            alert.setContentText("Deleted " + selectedScore.getEvent() + " " + selectedScore.getTeam().getName());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a score to delete.");
            alert.showAndWait();
        }
    }
}


