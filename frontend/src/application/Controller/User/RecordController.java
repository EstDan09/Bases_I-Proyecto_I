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
    
    @FXML
    private void filter(ActionEvent event) {
        // Your filtering logic
        System.out.println("Filter button clicked!");
    }

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

        loadTable();
			//loadSport();
			//loadOlympicGames();
        table.setItems(recordList);
		
	}


    // LOADERS =========================================================================

	private void loadTable() {
	    // Set default values for parameters if ComboBox selections are null
	    //String selectedSport = sportCB.getValue() != null ? sportCB.getValue() : "all";
	    //int selectedOlympicYear = categoryCB.getValue() != null ? Integer.parseInt(categoryCB.getValue()) : 0;

	    try {
	        // Fetch records from the database based on the selected filters
	        List<String[]> list = ConnectDB.getRecords("all", 0);

	        // Clear existing entries to avoid duplicate rows
	        recordList.clear();

	        // Populate recordList with data from the result set
	        for (String[] data : list) {
	            String name = data[0];        // athlete_name
	            String surname = data[1];     // last_name
	            String country = data[2];     // country_name
	            String sport = data[3];       // sport_name
	            String category = data[4];    // category_title
	            String record = data[5];      // highest_record
	            String date = data[6];        // event_date
	            String olympic = data[7];     // olympic_name

	            // Add each record as a new Record object to the observable list
	            recordList.add(new Record(name, surname, country, sport, category, record, date, olympic));
	        }
	        
	        System.out.println("Loaded records successfully. Total records: " + recordList.size());

	    } catch (SQLException e) {
	        System.out.println("Error loading records: " + e.getMessage());
	        e.printStackTrace();
	    }
	}




  
	

    
    


}

