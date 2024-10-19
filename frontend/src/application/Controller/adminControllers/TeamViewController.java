package application.Controller.adminControllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import DB.TeamsDB;
import application.Model.People;
import application.Model.Teams;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TeamViewController {

    // FXML linked UI components
    @FXML
    private TableView<Teams> teamsTableView;
    
    @FXML
    private TableColumn<Teams, Integer> id;
    
    @FXML
    private TableColumn<Teams, String> name;
    
    @FXML
    private TableColumn<Teams, String> trainer;
    
    @FXML
    private TableColumn<Teams, String> athletes;

    @FXML
    private Button addBtn;
    
    @FXML
    private Button editBtn;
    
    @FXML
    private Button deleteBtn;

    // Initialization method that runs after FXML elements are loaded
    @FXML
    public void initialize() throws SQLException {
        // Initialize the columns with the corresponding fields from the Team class
    	id.setCellValueFactory(new PropertyValueFactory<Teams, Integer>("id"));
    	name.setCellValueFactory(new PropertyValueFactory<Teams, String>("name"));                                
        trainer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTrainer().getName()));
        athletes.setCellValueFactory(cellData -> {
            List<People> athletes = cellData.getValue().getAthletes();
            String athletesString = athletes.stream()
                    .map(People::getName)  // Extract team names
                    .collect(Collectors.joining(", "));  // Join them as a single string
            return new SimpleStringProperty(athletesString);
        });    

     // Populate the table with data from the DB
        ObservableList<Teams> teamsList = TeamsDB.getTeamList();	            

        // Set the data in the TableView
        teamsTableView.setItems(teamsList);
    }

    // Action method for Add button
    @FXML
    public void addItem(ActionEvent event) throws IOException {
        /// Load the FXML
        Parent root = FXMLLoader.load(getClass().getResource("../../Views/Admin/EditTeams.fxml"));
        
        // Set the new content in the center of your content panel
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../../application.css").toExternalForm());
        stage.setTitle("Add Team");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    // Action method for Edit button
    @FXML
    public void editItem(ActionEvent event) throws IOException, SQLException {
    	// Get the selected Team
    	Teams selectedTeam = teamsTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            // Create FXMLLoader instance
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Views/Admin/EditTeams.fxml"));  
            
            // Load the FXML
            Parent root = loader.load();

            // Get the controller of the new scene
            EditTeamController controller = loader.getController();

            // Pass the parameter to the controller's method
            controller.setId(selectedTeam.getId());

            // Set the new content in the center of your content panel
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../../application.css").toExternalForm());
            stage.setTitle("Edit Team");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an team to edit.");
            alert.showAndWait();
        }
    }

    // Action method for Delete button
    @FXML
    public void deleteItem() {
        // Delete selected item logic
        System.out.println("Delete button clicked");
        Teams selectedTeam = teamsTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            //teamList.remove(selectedTeam);
        }
    }    
}
