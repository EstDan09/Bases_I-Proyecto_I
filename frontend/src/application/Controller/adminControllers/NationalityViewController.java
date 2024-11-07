package application.Controller.adminControllers;

import java.io.IOException;
import java.sql.SQLException;

import application.ConnectDB;
import application.Model.Nationality;
import application.Model.People;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NationalityViewController {

    // Define FXML elements
    @FXML
    private Label tittle;

    @FXML
    private TableView<Nationality> nationalityTableView;

    @FXML
    private TableColumn<Nationality, Integer> id;

    @FXML
    private TableColumn<Nationality, String> nationality;

    @FXML
    private Button addBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button deleteBtn;
    
    // Initialize method to set up the table columns and data
    @FXML
    public void initialize() throws SQLException {
    	
    	id.setCellValueFactory(new PropertyValueFactory<Nationality, Integer>("id"));
        nationality.setCellValueFactory(new PropertyValueFactory<Nationality, String>("name"));    	    
        
    	// Populate the table with data from the DB
        ObservableList<Nationality> nationalityList = DB.NationalityDB.getNationalityList();	
    	
        nationalityTableView.setItems(nationalityList);    
       
    }

    // Add new item action
    @FXML
    private void addItem(ActionEvent event) throws IOException {
    	// Load the FXML
        Parent root = FXMLLoader.load(getClass().getResource("../../Views/Admin/EditNationality.fxml"));
        
        // Set the new content in the center of your content panel
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../../application.css").toExternalForm());
		stage.setTitle("Nationality");
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
    }

    // Edit selected item action
    @FXML
    private void editItem(ActionEvent event) throws IOException, SQLException {
        Nationality selected = nationalityTableView.getSelectionModel().getSelectedItem();

        if (selected != null) {
                        
            // Create FXMLLoader instance
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Views/Admin/EditNationality.fxml"));  
            
            // Load the FXML
            Parent root = loader.load();

            // Get the controller of the new scene
            EditNationalityController controller = loader.getController();

            // Pass the parameter to the controller's method
            controller.setId(selected.getId());

            // Set the new content in the center of your content panel
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("../../application.css").toExternalForm());
    		stage.setTitle("Nationality");
    		stage.setScene(scene);
    		stage.centerOnScreen();
    		stage.show();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a Nationality to edit.");
            alert.showAndWait();
        }
    }

    // Delete selected item action
    @FXML
    private void deleteItem() throws SQLException {    	
        Nationality selected = nationalityTableView.getSelectionModel().getSelectedItem();              
        if (selected != null) {
        	ConnectDB.deleteNationality(selected.getName());            
        	nationalityTableView.getItems().remove(selected);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Delete Item");
            alert.setHeaderText(null);
            alert.setContentText("Deleted " + selected.getName());
            alert.showAndWait();
        } else {
        	Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a Nationality to delete.");
            alert.showAndWait();
        }
    }

    // Utility method to show alert dialogs
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
