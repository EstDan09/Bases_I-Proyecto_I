package application.Controller.adminControllers;

import java.io.IOException;
import java.sql.SQLException;

import application.Model.People;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditPeopleController {
	private int id;
	
	// Method to accept a parameter id
    public void setId(int id) throws SQLException {
        this.id = id;
        if (this.id != 0) { 
    		title.setText("Edit People");
    		People editPeople = DB.PeopleDB.getPeopleById(this.id);
    		first_name.setText(editPeople.getName());
    		last_name.setText(editPeople.getSurename());
    		nationality.setValue(editPeople.getRepresenting());
    	}
    }

    @FXML
    private TextField first_name;

    @FXML
    private TextField last_name;

    @FXML
    private TextField identification;

    @FXML
    private ComboBox<String> documentType;

    @FXML
    private ComboBox<String> nationality;

    @FXML
    private DatePicker birth_date;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private ComboBox<String> country;

    @FXML
    private ComboBox<String> province;

    @FXML
    private ComboBox<String> region;

    @FXML
    private ComboBox<String> district;

    @FXML
    private TextField phone;

    @FXML
    private TextField phone_2;

    @FXML
    private TextField username;
    
    @FXML
    private Label usernameLabel;

    @FXML
    private PasswordField password;
    
    @FXML
    private Label passwordLabel;

    @FXML
    private TextField email;

    @FXML
    private Label emailLabel;
    
    @FXML
    private Button saveBtn;

    @FXML
    private Label errorMsg;

    @FXML
    private Button photo;

    @FXML
    private ComboBox<String> type;

    @FXML
    private Hyperlink loginBtn;
    
    @FXML
    private Label title;

    // Initialize the combo boxes with some values
    @FXML
    public void initialize() throws SQLException {    	
        
        documentType.getItems().addAll("ID Card", "Passport", "Driver's License");
        nationality.getItems().addAll("Costa Rica", "USA", "Canada");
        ObservableList<String> genderList = DB.GenderDB.getGenderList();	
        gender.setItems(genderList);        
        country.getItems().addAll("Costa Rica", "USA", "Canada");
        province.getItems().addAll("San José", "Alajuela", "Cartago");
        region.getItems().addAll("Central", "Pacific", "Atlantic");
        district.getItems().addAll("Carmen", "Merced", "Uruca");
        type.getItems().addAll("Admin", "Athlete", "Trainer");
    }

    // Save button event handler
    @FXML
    private void savePerson(ActionEvent event) throws IOException, SQLException {
        // Validate input fields
        if (first_name.getText().isEmpty() || last_name.getText().isEmpty() || identification.getText().isEmpty()) {
            errorMsg.setText("Please fill in all required fields");
            errorMsg.setVisible(true);
            return;
        }

        // Hide error message if all fields are filled
        errorMsg.setVisible(false);
        
        People currentPeople = new People("0", first_name.getText(), last_name.getText(), nationality.getValue(), "2024", "Athlete");
        
        //Save into DB
        if(this.id == 0) {
        	currentPeople.setId(Integer.toString(this.id));
        	DB.PeopleDB.editPeople(currentPeople);
        }else {
        	
            DB.PeopleDB.createPeople(currentPeople);

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
}
