package application.Model;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EventController implements Initializable{

    @FXML
    private TableColumn<Event, String> category;

    @FXML
    private TableColumn<Event, String> date;

    @FXML
    private TableColumn<Event, String> event;

    @FXML
    private TableColumn<Event, String> sport;

    @FXML
    private TableColumn<Event, String> startingTime;

    @FXML
    private Label subtitle;

    @FXML
    private TableView<Event> table;
    
    ObservableList<Event> eventList = FXCollections.observableArrayList(
    		new Event("Final Badminton", "Femenine", "Badminton", "19/09/24", "19:00"),
    		new Event("Final Gymnastic Show", "Femenine", "Gymnastic", "20/09/24", "20:00")
	);
    

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		event.setCellValueFactory(new PropertyValueFactory<Event, String>("event"));
		category.setCellValueFactory(new PropertyValueFactory<Event, String>("category"));
		sport.setCellValueFactory(new PropertyValueFactory<Event, String>("sport"));
		date.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
		startingTime.setCellValueFactory(new PropertyValueFactory<Event, String>("startingTime"));
		
		table.setItems(eventList);
		
	}}
