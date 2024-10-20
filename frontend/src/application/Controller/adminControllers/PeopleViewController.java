package application.Controller.adminControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import application.Model.People;

import java.io.IOException;
import java.sql.SQLException;
import DB.PeopleDB;

public class PeopleViewController {

    @FXML
    private TableView<People> peopleTableView;

    @FXML
    private TableColumn<People, String> peopleIdColumn;

    @FXML
    private TableColumn<People, String> nameColumn;

    @FXML
    private TableColumn<People, String> surenameColumn;

    @FXML
    private TableColumn<People, String> representingColumn;

    @FXML
    private TableColumn<People, String> nationalityColumn;
    
    @FXML
    private TableColumn<People, String> typeColumn;

    @FXML
    private Button addBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button backBtn;
    
    @FXML
	BorderPane contentPanel;

    // Initialize the controller and set up the TableView
    @FXML
    public void initialize() throws SQLException {
    	peopleIdColumn.setCellValueFactory(new PropertyValueFactory<People, String>("id"));
    	nameColumn.setCellValueFactory(new PropertyValueFactory<People, String>("first_name"));
    	surenameColumn.setCellValueFactory(new PropertyValueFactory<People, String>("last_name"));
    	representingColumn.setCellValueFactory(new PropertyValueFactory<People, String>("country"));
    	nationalityColumn.setCellValueFactory(new PropertyValueFactory<People, String>("nationality"));
    	typeColumn.setCellValueFactory(new PropertyValueFactory<People, String>("typerole"));
        
    	// Populate the table with data from the DB
        ObservableList<People> peopleList = DB.PeopleDB.getPeopleList();	
    	
        peopleTableView.setItems(peopleList);        

    }

    // Action method for adding a new item
    @FXML
    void addItem(ActionEvent event) throws IOException {
    	
        // Load the FXML
        Parent root = FXMLLoader.load(getClass().getResource("../../Views/Admin/EditPeople.fxml"));
        
        // Set the new content in the center of your content panel
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../../application.css").toExternalForm());
		stage.setTitle("People");
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
    }

    // Action method for editing an existing item
    @FXML
    void editItem(ActionEvent event) throws IOException, NumberFormatException, SQLException {
        // Get the selected People
        People selectedPeople = peopleTableView.getSelectionModel().getSelectedItem();
        if (selectedPeople != null) {
                        
            // Create FXMLLoader instance
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Views/Admin/EditPeople.fxml"));  
            
            // Load the FXML
            Parent root = loader.load();

            // Get the controller of the new scene
            EditPeopleController controller = loader.getController();

            // Pass the parameter to the controller's method
            controller.setId(selectedPeople.getId());

            // Set the new content in the center of your content panel
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("../../application.css").toExternalForm());
    		stage.setTitle("People");
    		stage.setScene(scene);
    		stage.centerOnScreen();
    		stage.show();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a People to edit.");
            alert.showAndWait();
        }
    }

    // Action method for deleting an item
    @FXML
    void deleteItem(ActionEvent event) throws NumberFormatException, SQLException {
        // Get the selected People
        People selectedPeople = peopleTableView.getSelectionModel().getSelectedItem();
        if (selectedPeople != null) {
            // Add logic for deleting the selected item
        	DB.PeopleDB.deletePeople(selectedPeople.getId());
        	
            peopleTableView.getItems().remove(selectedPeople);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Delete Item");
            alert.setHeaderText(null);
            alert.setContentText("Deleted " + selectedPeople.getFirst_name());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a People to delete.");
            alert.showAndWait();
        }
    }
}
