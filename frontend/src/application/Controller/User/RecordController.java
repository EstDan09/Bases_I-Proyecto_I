package application.Controller.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import application.ConnectDB;
import application.Model.Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javax.swing.JOptionPane;

public class RecordController implements Initializable {

    @FXML
    private TableView<Record> table;

    @FXML
    private TableColumn<Record, String> nameColumn;

    @FXML
    private TableColumn<Record, String> surnameColumn;

    @FXML
    private TableColumn<Record, String> countryColumn;

    @FXML
    private TableColumn<Record, String> sportColumn;

    @FXML
    private TableColumn<Record, String> categoryColumn;

    @FXML
    private TableColumn<Record, String> recordColumn;

    @FXML
    private TableColumn<Record, String> dateColumn;

    @FXML
    private TableColumn<Record, String> olympicColumn;

    @FXML 
    private ComboBox<String> sportCB;

    @FXML
    private ComboBox<String> categoryCB;

    // Sample data for demonstration
    private final ObservableList<Record> recordList = FXCollections.observableArrayList(
        new Record("Michael", "Phelps", "USA", "Swimming", "Men's Freestyle 200m", "13seconds", "2008-08-10", "Beijing"),
        new Record("Usain", "Bolt", "Jamaica", "Athletics", "Men's Sprint 100m", "10seconds", "2008-08-16", "Beijing"),
        new Record("Simone", "Biles", "USA", "Gymnastics", "Women's All-Around", "15 points", "2016-08-11", "Rio")
    );

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        // Set up the columns to display the properties of the Record class
        nameColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("surname"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("country"));
        sportColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("sport"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("category"));
        recordColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("record"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("date"));
        olympicColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("olympic"));

        try {
			loadEvents();
			loadSport();
			loadOlympicGames();
		} catch (SQLException e) {
			System.out.println("Error: couldnt connect with the DB");
		}
        table.setItems(recordList);
		
	}


    // LOADERS =========================================================================

    private void loadEvents() throws SQLException {
		try {
			List<String[]> list = ConnectDB.getRecords(ConnectDB.getSportId("atleti"), 0);
			for (String[] data: list) {
				String name = data[2];
				String surname= "";
				String country = data[3];
				String sport = data[0];
				String category = "";
				String record = data[4];
                String date = data[5];
				String olympic = data[6];
				recordList.add(new Record(name, surname, country, sport, category, record, date, olympic));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

    // LOADER FOR SPORT COMBOBOX
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
		List<String[]> categoryList = null;
		try {
			categoryList = (List<String[]>) ConnectDB.getOlympicDetails();
			for (String[] o: categoryList) {
				categoryCB.getItems().add(o[3] + o[1]);
			} 
		} catch (SQLException ex) {
			System.out.println("No data in Olympic Table");
		}	
	}

    public void filter(ActionEvent action) {
        if (sportCB.getValue() == null || categoryCB.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Please select a sport and a category");
        } else {
            int cId= Integer.parseInt(categoryCB.getValue().substring(0, 2));
            
            recordList.clear();
            try {
                List<String[]> list = ConnectDB.getRecords(ConnectDB.getSportId(sportCB.getValue()), cId);
			for (String[] data: list) {
				String name = data[2];
				String surname= "";
				String country = data[3];
				String sport = data[0];
				String category = "";
				String record = data[4];
                String date = data[5];
				String olympic = data[6];
				recordList.add(new Record(name, surname, country, sport, category, record, date, olympic));
			}

            } catch (SQLException e) {
				System.out.println("Error: couldnt connect with the DB");
			}
        }
    }


}

