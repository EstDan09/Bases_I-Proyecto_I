package application.Controller.adminControllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import application.Model.Category;
import application.Model.Event;
import application.Model.Sport;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

public class EditEventController {
    private int id;

    // Method to accept a parameter id
    public void setId(int id) throws SQLException {
        this.id = id;
        if (this.id != 0) {
            title.setText("Edit Event");
            Event editEvent = DB.EventsDB.getEventById(this.id);
            nameEvent.setText(editEvent.getName());
            selectCategoryById(editEvent.getCategory().getId());            
            selectSportById(editEvent.getSport().getId());
            teamList.setItems(FXCollections.observableArrayList(editEvent.getParticipants()));
            dateEvent.setValue(LocalDate.parse(editEvent.getDate()));
            timeEvent.setText(editEvent.getStartingTime());
        }
    }
    
    private void selectSportById(int id) {
        for (Sport sport : sportEvent.getItems()) {
            if (sport.getId() == id) {
            	sportEvent.setValue(sport);  
                break;  
            }
        }
    }
    
    private void selectCategoryById(int id) {
        for (Category category : categoryEvent.getItems()) {
            if (category.getId() == id) {
            	categoryEvent.setValue(category);  
                break;
            }
        }
    }

    @FXML
    private TextField nameEvent;

    @FXML
    private ComboBox<Category> categoryEvent;

    @FXML
    private ComboBox<Sport> sportEvent;

    @FXML
    private ComboBox<Teams> selectTeam;

    @FXML
    private DatePicker dateEvent;
    
    @FXML
    private ListView<Teams> teamList;

    @FXML
    private TextField timeEvent;

    @FXML
    private Button saveBtnEvent;
    
    @FXML
    private Button addTeamBtn;
    
    @FXML
    private Button removeTeamBtn;

    @FXML
    private Label errorMsg;
    
    @FXML
    private Label title;


    // Initialize the combo boxes with some values
    @FXML
    public void initialize() throws SQLException {
    	
    	ObservableList<Sport> sportList = DB.SportDB.getSportList();	 
    	sportEvent.setItems(sportList);
    	
    	ObservableList<Category> categoryList = DB.CategoryDB.getCategoryList();	 
    	categoryEvent.setItems(categoryList); 
    	
    	ObservableList<Teams> teamList = DB.TeamsDB.getTeamList();	 
    	selectTeam.setItems(teamList);         
    }

    // Save button event handler
    @FXML
    private void saveEvent(ActionEvent event) throws IOException, SQLException {
        // Validate input fields
        if (nameEvent.getText().isEmpty() || categoryEvent.getValue() == null || sportEvent.getValue() == null || teamList.getItems().isEmpty() || dateEvent.getValue() == null || timeEvent.getText().isEmpty()) {
            errorMsg.setText("Please fill in all required fields");
            errorMsg.setVisible(true);
            return;
        }
        
        if (!nameEvent.getText().matches("^[a-zA-Z0-9 ]+$")) {
            errorMsg.setText("The name must not contain numbers or symbols.");
            errorMsg.setVisible(true);
            return;
        }
        
        if (dateEvent.getValue().isAfter(LocalDate.now())) {
            errorMsg.setText("The date of birth cannot be in the future.");
            errorMsg.setVisible(true);
            return;
        }
        
        if (!timeEvent.getText().matches("^[0-9: ]+$")) {
            errorMsg.setText("The event time should only contain digits and ':' (exam 14:30).");
            errorMsg.setVisible(true);
            return;
        }

              
        
        // Hide error message if all fields are filled
        errorMsg.setVisible(false);

        Event currentEvent = new Event(
        		0, 
        		nameEvent.getText(), 
        		categoryEvent.getValue(), 
        		sportEvent.getValue(), 
        		dateEvent.getValue().toString(), 
        		timeEvent.getText(), 
        		teamList.getItems());

        // Save into DB
        if (this.id == 0) {
            DB.EventsDB.addEvent(currentEvent); 
        } else {
            currentEvent.setId(this.id);
            DB.EventsDB.updateEvent(currentEvent); 
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

    // Login button event handler (Switch to login scene)
    @FXML
    private void switchToLoginScene(ActionEvent event) {
        // Add code to switch to the login scene
        System.out.println("Switching to login scene");
    }
    
    @FXML
    private void addTeam(ActionEvent event) {
    	teamList.getItems().add(selectTeam.getValue());    	
    }
    
    // Action method for Remove team button
    @FXML
    public void removeTeam() {
        Teams selectedTeam = teamList.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
        	teamList.getItems().remove(selectedTeam);
        }
    }
}
