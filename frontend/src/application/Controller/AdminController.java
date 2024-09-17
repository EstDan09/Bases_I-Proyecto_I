package application.Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class AdminController {
	
	@FXML
	BorderPane contentPanel;
	
	private Parent root;
	
	public void changeToPeopleScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/Admin/PeopleView.fxml"));
		contentPanel.setCenter(root);
		
	}
	
	public void changeToOlympicScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/Admin/OlympicView.fxml"));
		contentPanel.setCenter(root);
		
	}
}
