//HOLA BENJI
package application.Controller.User;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import application.ConnectDB;
import application.Model.Event2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
			loadSport();
			loadOlympicGames();
		} catch (SQLException e) {
			System.out.println("Error: couldnt connect with the DB");
		}
		table.setItems(eventList);
    }
		//
		
	
	
	// LOADERS ==================================================
	//             0     "0001-01-01"   0
	// filter by olympic, date,         sport
	private void loadEvents() throws SQLException {
		try {
			List<String[]> list = ConnectDB.getAllOlympicEvents(0, "0001-01-01", 0);
			for (String[] data: list) {
				String name = data[0];
				String category= data[2];
				String sport = data[1];
				String date = data[3];
				String time = data[4];
				String participants = data[5];
				eventList.add(new Event2(0,name, category, sport, date, time, participants));
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

	
	public void filter(ActionEvent event) throws Throwable, SQLException {
		if (dateChooser.getValue() == null || olympicCB.getValue() == null || sportCB.getValue() == null) {
			JOptionPane.showMessageDialog(null, "Please fill all the fields");
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String date = dateChooser.getValue() != null ? dateChooser.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
			String olympic = olympicCB.getValue();
			String sport = sportCB.getValue();
			int olyId = ConnectDB.getOlympicId(Integer.parseInt(olympic));
			int sportId = ConnectDB.getSportId(sport);
			eventList.clear();
			try {
				List<String[]> list = ConnectDB.getAllOlympicEvents(olyId, date, sportId);
				for (String[] data: list) {
					String name = data[0];
					String category= data[2];
					String sport1 = data[1];
					String datew = data[3];
					String time = data[4];
					String participants = data[5];
					eventList.add(new Event2(0,name, category, sport1, datew, time, participants));
				}
			} catch (SQLException e) {
				System.out.println("Error: couldnt connect with the DB");
			}
		}
			
	}
	
}