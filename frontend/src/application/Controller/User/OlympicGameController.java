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
        name.setCellValueFactory(new PropertyValueFactory<OlympicGame, String>("name"));
        host.setCellValueFactory(new PropertyValueFactory<OlympicGame, String>("host"));
        year.setCellValueFactory(new PropertyValueFactory<OlympicGame, Integer>("year"));
        totalParticipants.setCellValueFactory(new PropertyValueFactory<OlympicGame, Integer>("totalParticipants"));
        totalCountries.setCellValueFactory(new PropertyValueFactory<OlympicGame, Integer>("totalCountries"));
        totalMedals.setCellValueFactory(new PropertyValueFactory<OlympicGame, Integer>("totalMedals"));
        totalEvents.setCellValueFactory(new PropertyValueFactory<OlympicGame, Integer>("totalEvents"));

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
		List<String[]> list = ConnectDB.getOlympicDetails();
		for (String[] game: list) {
			String name = game[0];
			String country = game[2];
			int year = Integer.parseInt(game[1]);
			int totalParticipants = Integer.parseInt(game[4]);
			int totalCountries = Integer.parseInt(game[5]);
			int totalMedals = Integer.parseInt(game[6]);
			int totalEvents = Integer.parseInt(game[3]);
			olympicGameList.add(new OlympicGame(name, country, year, totalParticipants, totalCountries, totalMedals, totalEvents));
		}
    }
}
