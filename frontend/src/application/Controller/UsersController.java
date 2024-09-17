package application.Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class UsersController {
	
	private Parent root;
	
	@FXML
	BorderPane contentPanel;
	
	public void changeToEventsScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/Users/EventsView.fxml"));
		contentPanel.setCenter(root);
	}
}
