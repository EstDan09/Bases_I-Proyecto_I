package application.Controller.adminControllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import application.ConnectDB;
import application.Model.Nationality;
import application.Model.People;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditNationalityController {
	private int id;
	
	private Nationality editNationality;
	// Method to accept a parameter id
    public void setId(int id) throws SQLException {
        this.id = id;
        if (this.id != 0) { 
    		title.setText("Edit Nationality");
    		editNationality = ConnectDB.getNationalityById(this.id);
    		nationality.setText(editNationality.getName());    		
    	}
    }

    @FXML
    private Hyperlink loginBtn;

    @FXML
    private Label title;

    @FXML
    private Button saveBtn;

    @FXML
    private Label errorMsg;

    @FXML
    private TextField nationality;

    // Method called when Save button is clicked
    @FXML
    private void saveNationality(ActionEvent event) throws SQLException, IOException {
    	
    	errorMsg.setVisible(false);
        errorMsg.setText("");
        
        // Validate input fields
        if (nationality.getText().isEmpty()) {
            errorMsg.setText("Please fill in all required fields");
            errorMsg.setVisible(true);
            return;
        }
        
        if (!nationality.getText().matches("[a-zA-Z]+")) {
            errorMsg.setText("The name must not contain numbers or symbols.");
            errorMsg.setVisible(true);
            return;
        }
    	
     // Hide error message if all fields are filled
        errorMsg.setVisible(false);
        
        Nationality currentNationality = new Nationality(
        		0,
        		nationality.getText());
                        		
        //Save into DB
        if(this.id != 0) {
        	currentNationality.setId(this.id);
        	ConnectDB.updateNationality(editNationality.getName(), currentNationality.getName());
        }else {
        	ConnectDB.registerNationality(nationality.getText());
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

    // Method called when Login hyperlink is clicked
    @FXML
    private void switchToLoginScene() {
        // Logic to switch to login scene
        System.out.println("Switching to login scene...");
    }

    // Helper method to show an error message
    private void showErrorMessage(String message) {
        errorMsg.setText(message);
        errorMsg.setVisible(true);
    }

    // Helper method to show a confirmation dialog
    private void showConfirmation(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
