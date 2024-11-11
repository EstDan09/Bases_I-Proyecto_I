package application.Controller.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import application.ConnectDB;
import application.Model.OlympicGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OlympicGameController implements Initializable {

    @FXML
    private TableColumn<OlympicGame, String> name;

    @FXML
    private TableColumn<OlympicGame, String> host;

    @FXML
    private TableColumn<OlympicGame, Integer> year;

    @FXML
    private TableColumn<OlympicGame, Integer> totalParticipants;

    @FXML
    private TableColumn<OlympicGame, Integer> totalCountries;

    @FXML
    private TableColumn<OlympicGame, Integer> totalMedals;
    
    @FXML
    private TableColumn<OlympicGame, Integer> totalEvents;

    @FXML
    private Label subtitle;

    @FXML
    private TableView<OlympicGame> table;

    // Sample Data for the Olympic Games
    ObservableList<OlympicGame> olympicGameList = FXCollections.observableArrayList(
        new OlympicGame("Dummy Olympic", "DummyLand", 2024, 1, 2, 3, 4)
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up the columns with the appropriate data properties
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	host.setCellValueFactory(new PropertyValueFactory<>("host"));
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));
    	totalParticipants.setCellValueFactory(new PropertyValueFactory<>("totalParticipants"));
    	totalCountries.setCellValueFactory(new PropertyValueFactory<>("totalCountries"));
    	totalMedals.setCellValueFactory(new PropertyValueFactory<>("totalMedals"));
    	totalEvents.setCellValueFactory(new PropertyValueFactory<>("totalEvents"));


        // Load the sample data into the TableView
        try {
			loadOlympicGames();
		} catch (SQLException e) {
			System.out.println("Error: couldnt connect with the DB");
		}
        table.setItems(olympicGameList);
    }
    
    
    // LOADERS ==================================================================================
    private void loadOlympicGames() throws SQLException {
        List<String[]> list = ConnectDB.getOlympicsSummary();
        for (String[] game : list) {
            String name = game[1]; // Olympic name
            String country = game[3]; // Country name
            int year = Integer.parseInt(game[2]); // Year
            int totalParticipants = Integer.parseInt(game[4]); // Total participants
            int totalCountries = Integer.parseInt(game[5]); // Total countries
            int totalMedals = Integer.parseInt(game[6]); // Total medals
            int totalEvents = Integer.parseInt(game[7]); // Total events

            // Add the OlympicGame object to the observable list
            olympicGameList.add(new OlympicGame(name, country, year, totalParticipants, totalCountries, totalMedals, totalEvents));
        }
    }


}
