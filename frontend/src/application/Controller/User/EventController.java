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

public class EventController implements Initializable {

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
            new Event2(0,"Final Badminton", "Feminine", "Badminton", "19/09/24", "19:00", "South Korea Team, Japan Team"),
            new Event2(0,"Final Gymnastic Show", "Feminine", "Gymnastic", "20/09/24", "20:00", "Russian Team, USA Team")
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        event.setCellValueFactory(new PropertyValueFactory<>("name"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        sport.setCellValueFactory(new PropertyValueFactory<>("sport"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        startingTime.setCellValueFactory(new PropertyValueFactory<>("startingTime"));
        participants.setCellValueFactory(new PropertyValueFactory<>("participants"));
        
        try {
            loadEvents();
            loadSport();
            loadOlympicGames();
        } catch (SQLException e) {
            System.out.println("Error: couldn't connect with the DB");
        }
        table.setItems(eventList);
    }

    // Load events without filters (initial view)
    private void loadEvents() throws SQLException {
        try {
            List<String[]> list = ConnectDB.getAllOlympicEvents("all", "", "");
            for (String[] data: list) {
                String name = data[0];
                String category = data[1];
                String sport = data[2];
                String date = data[3];
                String time = data[4];
                String participants = data[5];
                eventList.add(new Event2(0, name, category, sport, date, time, participants));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Load sport names into ComboBox
    private void loadSport() {
        try {
            List<String[]> sports = ConnectDB.getAllSports();
            for (String[] s : sports) {
                sportCB.getItems().add(s[0]);
            }
        } catch (SQLException ex) {
            System.out.println("No data in sport Table");
        }   
    }

    // Load Olympic game names into ComboBox
    private void loadOlympicGames() {
        try {
            List<String[]> olympicList = ConnectDB.getOlympicDetails();
            for (String[] o : olympicList) {
                olympicCB.getItems().add(o[1]);
            }
        } catch (SQLException ex) {
            System.out.println("No data in Olympic Table");
        }   
    }

    // Filter events based on ComboBox selections and date
    public void filter(ActionEvent event) {
        if (dateChooser.getValue() == null || olympicCB.getValue() == null || sportCB.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
        } else {
            String date = dateChooser.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String olympic = olympicCB.getValue();
            String sport = sportCB.getValue();
            String choice = "both";  // This is set based on all filters applied

            eventList.clear();
            try {
                List<String[]> list = ConnectDB.getAllOlympicEvents(choice, olympic, sport);
                for (String[] data : list) {
                    String name = data[0];
                    String category = data[1];
                    String sport1 = data[2];
                    String datew = data[3];
                    String time = data[4];
                    String participants = data[5];
                    eventList.add(new Event2(0, name, category, sport1, datew, time, participants));
                }
            } catch (SQLException e) {
                System.out.println("Error: couldn't connect with the DB");
            }
        }
    }
}
