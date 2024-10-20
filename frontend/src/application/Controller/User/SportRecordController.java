package application.Controller.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.ConnectDB;
import application.Model.SportRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
	ObservableList<SportRecord> dummyList = FXCollections.observableArrayList(
			new SportRecord("Badminton", "Benji", "15 points", "China")
			);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        // Set up the columns with the appropriate data properties
		sport.setCellValueFactory(new PropertyValueFactory<SportRecord, String>("sport"));
		competitor.setCellValueFactory(new PropertyValueFactory<SportRecord, String>("competitor"));
		score.setCellValueFactory(new PropertyValueFactory<SportRecord, String>("record"));
		country.setCellValueFactory(new PropertyValueFactory<SportRecord, String>("country"));
		
        // Load the sample data into the TableView
        table.setItems(dummyList);
    }
	
	// LOADERS ====================================================================
	private void loadOlympicGames() {
//		List<String> games = null;
//		try {
//			games = ConnectDB.getOlympicDetails();
//			for (String g : games) {
//			    olympicCB.getItems().add(g);  // Add each typeId to the ComboBox
//			}
//		} catch (SQLException e) {
//			System.out.println("No data in Olympics table");
//		}  
	}
	
	private void loadTable() {
		
	}
}
