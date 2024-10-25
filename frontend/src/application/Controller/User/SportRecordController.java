package application.Controller.User;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import application.ConnectDB;
import application.Model.Event2;
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
	
    private static final String JOptionPane = null;

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
    
	ObservableList<SportRecord> topScoresList = FXCollections.observableArrayList(
			
			);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        // Set up the columns with the appropriate data properties
		sport.setCellValueFactory(new PropertyValueFactory<SportRecord, String>("sport"));
		competitor.setCellValueFactory(new PropertyValueFactory<SportRecord, String>("competitor"));
		score.setCellValueFactory(new PropertyValueFactory<SportRecord, String>("record"));
		country.setCellValueFactory(new PropertyValueFactory<SportRecord, String>("country"));
		loadTable();
		loadSport();
		loadOlympicGames();
        // Load the sample data into the TableView
        table.setItems(topScoresList);
    }
	
	// LOADERS ====================================================================
	private void loadTable() {
		try {
			List<String[]> list = ConnectDB.getTopScores(ConnectDB.getOlympicId(1974), ConnectDB.getSportId("atleti"));
			for (String[] data: list) {
				String sport = data[0];
				String competi= data[2];
				String score = data[4];
				String coutry = data[3];
				topScoresList.add(new SportRecord(sport, competi, score, coutry));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void filter(ActionEvent event) throws Throwable, SQLException {
		if (olympicCB.getValue() == null || sportCB.getValue() == null) {
		} else {
			String olympic = olympicCB.getValue();
			String sport = sportCB.getValue();
			int olyId = ConnectDB.getOlympicId(Integer.parseInt(olympic));
			int sportId = ConnectDB.getSportId(sport);
			topScoresList.clear();
			try {
				List<String[]> list = ConnectDB.getTopScores(olyId, sportId);
				for (String[] data: list) {
					String sport1 = data[0];
					String competi= data[2];
					String score = data[4];
					String coutry = data[3];
					topScoresList.add(new SportRecord(sport1, competi, score, coutry));
				}
			} catch (SQLException e) {
				System.out.println("Error: couldnt connect with the DB");
			}
		}
			
	}

    private void loadSport() {
		List<String[]> sports = null;
		try {
			sports = (List<String[]>) ConnectDB.getAllSports();
			for (String[] s: sports) {
				sportCB.getItems().add(s[0]);
			} 
		} catch (SQLException ex) {
			System.out.println("No data in sport Table");
		}	
	}

	// LOADER FOR OLYMPIC COMBOBOX
	private void loadOlympicGames() {
		List<String[]> olympicList = null;
		try {
			olympicList = (List<String[]>) ConnectDB.getOlympicDetails();
			for (String[] o: olympicList) {
				olympicCB.getItems().add(o[1]);
			} 
		} catch (SQLException ex) {
			System.out.println("No data in Olympic Table");
		}	
	}
}
