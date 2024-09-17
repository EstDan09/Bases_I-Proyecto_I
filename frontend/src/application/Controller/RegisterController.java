package application.Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RegisterController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Label errorMsg;
	@FXML
	private Hyperlink loginBtn;
	@FXML
	private Button registerBtn;
	
	public void switchToLoginScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/LoginView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Olympics | Login");
		stage.setScene(scene);
		stage.show();
	}
	
	public void register() {
		System.out.println("Register");
		errorMsg.setText("ERROR: No connection with the system");
		errorMsg.setVisible(true);
	}
	
	
}