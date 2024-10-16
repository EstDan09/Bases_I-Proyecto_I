package application.Controller;

import java.io.IOException;

import application.Model.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UsersController {
	
	private Parent root;
	
	@FXML
	BorderPane contentPanel;
	
	public void changeToEventsScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/Users/EventsView.fxml"));
		contentPanel.setCenter(root);
	}
	
	public void changeToTrainersScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/Users/Trainers.fxml"));
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
	
	public void changeToOlympicScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/Users/OlympicGames.fxml"));
		contentPanel.setCenter(root);
	}
}
