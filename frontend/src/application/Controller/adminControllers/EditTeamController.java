package application.Controller.adminControllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import application.Model.Country;
import application.Model.People;
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

public class EditTeamController {
	
	private int id;

    // Method to accept a parameter id
    public void setId(int id) throws SQLException {
        this.id = id;
        if (this.id != 0) {
            title.setText("Edit Event");
            Teams editTeam = DB.TeamsDB.getTeamById(this.id);
            teamName.setText(editTeam.getName());
            selectCountry.setValue(editTeam.getCountry());
            selectTrainer.setValue(editTeam.getTrainer());   
            athleteList.setItems(FXCollections.observableArrayList(editTeam.getAthletes()));
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
    private TextField teamName;

    @FXML
    private Label errorMsg;

    @FXML
    private ComboBox<People> selectTrainer;

    @FXML
    private ComboBox<Country> selectCountry;

    @FXML
    private ComboBox<People> selectAthlete;

    @FXML
    private ListView<People> athleteList;

    @FXML
    private Button addAthleteBtn;

    @FXML
    private Button removeAthleteBtn;
    
    // Initialization method that runs after FXML elements are loaded
    @FXML
    public void initialize() throws SQLException {
        // Populate ComboBoxes    	
    	ObservableList<Country> countryList = DB.CountryDB.getCountryList();	 
    	selectCountry.setItems(countryList);         

        // Clear error message
        errorMsg.setVisible(false);
    }

    // Action method for Save button
    @FXML
    public void saveTeam(ActionEvent event) throws SQLException, IOException {
    	 // Validate input fields
    	if (teamName.getText().isEmpty()) {
            errorMsg.setText("Please fill in all required fields");
            errorMsg.setVisible(true);
            return;
        }
    	String teamNameText = teamName.getText();
    	if (!teamNameText.matches("[a-zA-Z]+")) {
    	    errorMsg.setText("Team name can only contain letters");
    	    errorMsg.setVisible(true);
    	    return;
    	}
    	
    	
        
        
    	List<People> athletes = athleteList.getItems();    	
    	
    	Teams currentTeam = new Teams(
    			0, 
    			teamName.getText(), 
    			selectCountry.getValue(),
    			selectTrainer.getValue(), 
    			athletes);
    	
    	
    	
    	
    	// Save into DB
        if (this.id == 0) {
            DB.TeamsDB.addTeam(currentTeam); 
        } else {
        	currentTeam.setId(this.id);
            DB.TeamsDB.updateTeam(currentTeam); 
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

    // Action method for Add Athlete button
    @FXML
    public void addAthlete() {
        People selectedAthlete = selectAthlete.getValue();
        if (selectedAthlete != null && !athleteList.getItems().contains(selectedAthlete)) {
            athleteList.getItems().add(selectedAthlete);
            selectAthlete.getSelectionModel().clearSelection(); // Clear selection
        }
    }

    // Action method for Remove Athlete button
    @FXML
    public void removeAthlete() {
        People selectedAthlete = athleteList.getSelectionModel().getSelectedItem();
        if (selectedAthlete != null) {
            athleteList.getItems().remove(selectedAthlete);
        }
    }

    // Action method for Login hyperlink
    @FXML
    public void switchToLoginScene() {
        // Logic to switch to login scene (this typically involves loading a new FXML file and changing the stage)
        System.out.println("Switching to login scene");
    }

    // Action method for ComboBox change
    @FXML
    public void changeSelectedCountry() throws SQLException {
        // Logic to handle country selection change, if needed
        Country selectedCountry = selectCountry.getValue();
        
        ObservableList<People> trainerList = DB.PeopleDB.getPeopleListByTypeAndCountry("Trainer", selectedCountry.getId());	 
    	selectTrainer.setItems(trainerList);     
    	
    	ObservableList<People> athletesList = DB.PeopleDB.getPeopleListByTypeAndCountry("Athlete", selectedCountry.getId());	 
    	selectAthlete.setItems(athletesList);     
        
    }
}
