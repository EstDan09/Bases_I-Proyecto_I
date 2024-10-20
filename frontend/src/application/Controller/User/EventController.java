package application.Controller.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import application.ConnectDB;
import application.Model.Event2;
import application.Model.OlympicGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EventController implements Initializable{

    @FXML
    private TableColumn<Event2, String> category;

    @FXML
    private TableColumn<Event2, String> date;

    @FXML
    private TableColumn<Event2, String> event;

    @FXML
    private TableColumn<Event2, String> sport;

    @FXML
    private TableColumn<Event2, String> startingTime;
    
    @FXML
    private ComboBox<String> olympicCB;
    
    @FXML
    private ComboBox<String> sportCB;
    
    @FXML
    private DatePicker dateChooser;

    @FXML
    private Button filterBtn;

    @FXML
    private Label subtitle;

    @FXML
    private TableView<Event2> table;
    
    @FXML
    private TableColumn<Event2, String> participants;
    
    ObservableList<Event2> eventList = FXCollections.observableArrayList(
    		new Event2(0,"Final Badminton", "Femenine", "Badminton", "19/09/24", "19:00", "South Korea Team, Japan Team"),
    		new Event2(0,"Final Gymnastic Show", "Femenine", "Gymnastic", "20/09/24", "20:00", "Russian Team, USA Team")
	);
    

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		event.setCellValueFactory(new PropertyValueFactory<Event2, String>("name"));
		category.setCellValueFactory(new PropertyValueFactory<Event2, String>("category"));
		sport.setCellValueFactory(new PropertyValueFactory<Event2, String>("sport"));
		date.setCellValueFactory(new PropertyValueFactory<Event2, String>("date"));
		startingTime.setCellValueFactory(new PropertyValueFactory<Event2, String>("startingTime"));
		participants.setCellValueFactory(new PropertyValueFactory<Event2, String>("participants"));
		
		try {
			loadEvents();
		} catch (SQLException e) {
			System.out.println("Error: couldnt connect with the DB");
		}
		table.setItems(eventList);
    }
		//
		
	
	
	// LOADERS ==================================================
	
	// filter by sport, olympic, date
	private void loadEvents() throws SQLException {
		try {
			List<String[]> list = ConnectDB.getAllOlympicEvents(0, "0001-01-01", 0);
			for (String[] data: list) {
				String name = data[0];
				String category= data[2];
				String sport = data[1];
				String date = data[3];
				String time = data[3];
				String participants = data[5];
				eventList.add(new Event2(0,name, category, sport, date, time, participants));
			}
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}