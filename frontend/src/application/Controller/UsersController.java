package application.Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class UsersController {
	
	private Parent root;
	
	@FXML
	BorderPane contentPanel;
	
	public void changeToEventsScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/Users/EventsView.fxml"));
		contentPanel.setCenter(root);
		
		Label subtitle = (Label)root.lookup("#subtitle");
		subtitle.setText("No events");
	}
	
	public void changeToHomeScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/Users/HomeView.fxml"));
		contentPanel.setCenter(root);
	}
	
	public void changeToSportsScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/Users/SportsView.fxml"));
		contentPanel.setCenter(root);
	}
	
	public void changeToGamesScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/Users/GamesView.fxml"));
		contentPanel.setCenter(root);
	}
	
	public void changeToRecordsScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/Users/RecordsView.fxml"));
		contentPanel.setCenter(root);
	}
	
	public void changeToCountriesScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/Users/CountriesView.fxml"));
		contentPanel.setCenter(root);
	}
	
	public void changeToAthletesScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/Users/AthletesView.fxml"));
		contentPanel.setCenter(root);
	}
}
