package application.Controller.User;

import java.net.URL;
import java.util.ResourceBundle;

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
        new OlympicGame("Tokyo 2020", "Japan", 2021, 11090, 205, 339, 500),
        new OlympicGame("Rio 2016", "Brazil", 2016, 11238, 207, 306, 456),
        new OlympicGame("Beijing 2008", "China", 2008, 123124, 100, 123, 5005)
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
        table.setItems(olympicGameList);
    }
}
