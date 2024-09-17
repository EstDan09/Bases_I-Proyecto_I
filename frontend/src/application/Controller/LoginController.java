package application.Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class LoginController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	ComboBox role;
	
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
		if (role.getValue() == "") {
			root = FXMLLoader.load(getClass().getResource("../Views/AdminView.fxml"));
			title = "Admin Dashboard";
		} else if (role.getValue() == null) {
			root = FXMLLoader.load(getClass().getResource("../Views/UsersView.fxml"));
		} else if (role.getValue() == "") {
			
		}

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
		stage.setTitle("Olympics | " + title);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}
	
	
}