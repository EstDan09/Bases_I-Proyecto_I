package application.Controller.adminControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import application.Model.Event;
import application.Model.Teams;

import java.io.IOException;
import java.sql.SQLException;
//import EventsDB; //CREAR
import java.util.List;
import java.util.stream.Collectors;

import DB.EventsDB;

public class EventsViewController {

    @FXML
    private TableView<Event> eventsTableView;

    @FXML
    private TableColumn<Event, String> eventIdColumn;

    @FXML
    private TableColumn<Event, String> eventColumn;

    @FXML
    private TableColumn<Event, String> categoryColumn;

    @FXML
    private TableColumn<Event, String> sportColumn;

    @FXML
    private TableColumn<Event, String> teamColumn;

    @FXML
    private TableColumn<Event, String> dateColumn;
    
    @FXML
    private TableColumn<Event, String> startingTimeColumn;

    @FXML
    private Button addBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button backBtn;

    @FXML
    private BorderPane contentPanel;

    // Initialize the controller and set up the TableView
    @FXML
    public void initialize() throws SQLException {
        eventIdColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("id"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));        
        sportColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSport().getName()));
        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getName()));
        teamColumn.setCellValueFactory(cellData -> {
            List<Teams> participants = cellData.getValue().getParticipants();
            String participantsString = participants.stream()
                    .map(Teams::getName)  // Extract team names
                    .collect(Collectors.joining(", "));  // Join them as a single string
            return new SimpleStringProperty(participantsString);
        });    
        //teamColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("team"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        startingTimeColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("startingTime"));
        
        // Populate the table with data from the DB
        ObservableList<Event> eventsList = EventsDB.getEventsList();	
        eventsTableView.setItems(eventsList);     
    }

    // Action method for adding a new item
    @FXML
    void addEvent(ActionEvent event) throws IOException {
        // Load the FXML
        Parent root = FXMLLoader.load(getClass().getResource("../../Views/Admin/EditEvent.fxml"));
        
        // Set the new content in the center of your content panel
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../../application.css").toExternalForm());
        stage.setTitle("Add Event");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    // Action method for editing an existing item
    @FXML
    void editEvent(ActionEvent event) throws IOException, NumberFormatException, SQLException {
        // Get the selected Event
    	Event selectedEvent = eventsTableView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            // Create FXMLLoader instance
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Views/Admin/EditEvent.fxml"));  
            
            // Load the FXML
            Parent root = loader.load();

            // Get the controller of the new scene
            EditEventController controller = loader.getController();

            // Pass the parameter to the controller's method
            controller.setId(selectedEvent.getId());

            // Set the new content in the center of your content panel
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../../application.css").toExternalForm());
            stage.setTitle("Edit Event");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an event to edit.");
            alert.showAndWait();
        }
    }

    // Action method for deleting an item
    @FXML
    void deleteEvent(ActionEvent event) throws NumberFormatException, SQLException {
        // Get the selected Event
        Event selectedEvent = eventsTableView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            // Add logic for deleting the selected item
            EventsDB.deleteEvent(selectedEvent.getId());
            
            eventsTableView.getItems().remove(selectedEvent);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Delete Item");
            alert.setHeaderText(null);
            alert.setContentText("Deleted " + selectedEvent.getName());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an event to delete.");
            alert.showAndWait();
        }
    }
}
