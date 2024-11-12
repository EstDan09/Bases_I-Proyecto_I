package application.Controller.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import application.ConnectDB;
import application.Model.Athlete;
import application.Model.OlympicGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AthleteController implements Initializable {

    @FXML
    private TableColumn<Athlete, Integer> age;

    @FXML
    private ComboBox<String> countryCB;

    @FXML
    private Button filterBtn;

    @FXML
    private TableColumn<Athlete, String> flag;

    @FXML
    private TableColumn<Athlete, String> name;

    @FXML
    private TableColumn<Athlete, String> olympic;

    @FXML
    private ComboBox<String> olympicCB;

    @FXML
    private TableColumn<Athlete, String> photo;

    @FXML
    private TableColumn<Athlete, String> representing;

    @FXML
    private TableColumn<Athlete, String> surname;

    @FXML
    private TableView<Athlete> table;

    // This should be a list of Trainer objects, not Event
    ObservableList<Athlete> athletesList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the columns with the respective properties from Trainer
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        olympic.setCellValueFactory(new PropertyValueFactory<>("olympic"));
        representing.setCellValueFactory(new PropertyValueFactory<>("representing"));
        // Add some sample data
        
        athletesList.addAll(
            new Athlete("John", "Doe", 45, "2024 Paris", "USA"),
            new Athlete("Anna", "Smith", 39, "2020 Tokyo", "Canada"),
            new Athlete("Liu", "Wang", 50, "2016 Rio", "China"),
            new Athlete("Maria", "Garcia", 42, "2024 Paris", "Spain")
        );
        try {
			loadAthletes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Set the items to the TableView
        table.setItems(athletesList);
    }
    
    private void loadAthletes() throws SQLException {
        List<String[]> list = ConnectDB.getAllAthletesB();
        for (String[] game : list) {
            String name = game[0];  
            String lname = game[1];  
            int year = Integer.parseInt(game[2]); 
            String olym = game[3];
            String country = game[4];

            // Add the OlympicGame object to the observable list
            athletesList.add(new Athlete(name, lname, year, olym, country));
        }
    }
}
