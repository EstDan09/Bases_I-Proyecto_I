package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.ConnectDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	ComboBox<String> role;
	
	@FXML
	Label errorLabel;
	
	@FXML
	TextField username;
	
	@FXML
	PasswordField password;
	
	public void switchToRegisterScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/RegisterView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Olympics | Register");
		stage.setScene(scene);
		stage.show();
	}
	
		
	public void login(ActionEvent event) throws IOException {
		String title = "";
		// CHANGE SCENE
		try {
			
			if (role.getValue() == "admin") {
				if (ConnectDB.login(role.getValue(), username.toString(), password.getText())) {
					root = FXMLLoader.load(getClass().getResource("../Views/AdminView.fxml"));
					title = "Admin Dashboard";
				} else {
					errorLabel.setText("Invalid user");
				}
			} else if (role.getValue() == "user") {
				root = FXMLLoader.load(getClass().getResource("../Views/UsersView.fxml"));
			} 
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
			stage.setTitle("Olympics | " + title);
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
			errorLabel.setText("Please choose a role");
			errorLabel.setVisible(true);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setData();
	}

	private void setData(){
		// ResultSet r = ConnectDB.getRoles()
		// while (r.next()) {
		//		role.getItems().add(r.getString("ROLE"));
		// }
		
		role.getItems().addAll(
				"admin",
		        "user");
	}
	
	
}