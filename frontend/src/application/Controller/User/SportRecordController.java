package application.Controller.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import application.ConnectDB;
import application.Model.SportRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SportRecordController implements Initializable {

    @FXML
    private TableColumn<SportRecord, String> competitor;

    @FXML
    private TableColumn<SportRecord, String> country;

    @FXML
    private Button filterBtn;

    @FXML
    private ComboBox<String> olympicCB;

    @FXML
    private TableColumn<SportRecord, String> score;

    @FXML
    private TableColumn<SportRecord, String> sport;

    @FXML
    private ComboBox<String> sportCB;

    @FXML
    private Label subtitle;

    @FXML
    private TableView<SportRecord> table;

    ObservableList<SportRecord> topScoresList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Set up the columns with the appropriate data properties
        sport.setCellValueFactory(new PropertyValueFactory<>("sport"));
        competitor.setCellValueFactory(new PropertyValueFactory<>("competitor"));
        score.setCellValueFactory(new PropertyValueFactory<>("record"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        loadSport();
        loadOlympicGames();
        loadTable();
        table.setItems(topScoresList);
    }

    // LOADERS ====================================================================
    private void loadTable() {
        String selectedSport = sportCB.getValue() != null ? sportCB.getValue() : "all";
        int selectedOlympicYear = olympicCB.getValue() != null ? Integer.parseInt(olympicCB.getValue()) : 0;

        try {
            List<String[]> list = ConnectDB.getTopScoresBySport(selectedSport, selectedOlympicYear);
            topScoresList.clear();
            for (String[] data : list) {
                String sportNameData = data[1];
                String competitorName = data[0];
                String scoreData = data[3];
                String countryName = data[2];
                topScoresList.add(new SportRecord(sportNameData, competitorName, scoreData, countryName));
            }
        } catch (SQLException e) {
            System.out.println("Error loading top scores: " + e.getMessage());
        }
    }

    // Filter action to apply the selected sport and Olympic filters
    public void filter(ActionEvent event) {
        if (olympicCB.getValue() == null || sportCB.getValue() == null) {
            System.out.println("Please select both Olympic year and Sport.");
        } else {
            loadTable(); // This will reload the table based on current filter selections
        }
    }

    // LOADER FOR SPORT COMBOBOX
    private void loadSport() {
        try {
            List<String[]> sports = ConnectDB.getAllSports();
            for (String[] s : sports) {
                sportCB.getItems().add(s[1]);
            }
        } catch (SQLException ex) {
            System.out.println("No data in sport Table");
        }   
    }

    // Load Olympic game names into ComboBox
    private void loadOlympicGames() {
        try {
            List<String[]> olympicList = ConnectDB.getOlympicsSummary();
            for (String[] o : olympicList) {
                olympicCB.getItems().add(o[2]);
            }
        } catch (SQLException ex) {
            System.out.println("No data in Olympic Table");
        }   
    }
}
