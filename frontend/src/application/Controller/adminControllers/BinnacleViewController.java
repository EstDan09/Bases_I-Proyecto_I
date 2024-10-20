package application.Controller.adminControllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import DB.EventsDB;
import application.Model.Binnacle;
import application.Model.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BinnacleViewController implements Initializable {

    @FXML
    private TableColumn<Binnacle, String> Id;

    @FXML
    private TableColumn<Binnacle, String> dateBin;

    @FXML
    private TableColumn<Binnacle, String> timeBin;

    @FXML
    private TableColumn<Binnacle, String> UserBin;

    @FXML
    private TableColumn<Binnacle, String> objectBin;

    @FXML
    private TableColumn<Binnacle, String> changeTypeBin;

    @FXML
    private TableColumn<Binnacle, String> descriptionBin;

    @FXML
    private Label subtitle;

    @FXML
    private TableView<Binnacle> binTableView;
    
    @FXML
    private DatePicker datefilter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up the columns with the appropriate data properties
        Id.setCellValueFactory(new PropertyValueFactory<Binnacle, String>("id"));
        dateBin.setCellValueFactory(new PropertyValueFactory<Binnacle, String>("dateBin"));
        timeBin.setCellValueFactory(new PropertyValueFactory<Binnacle, String>("timeBin"));
        UserBin.setCellValueFactory(new PropertyValueFactory<Binnacle, String>("userBin"));
        objectBin.setCellValueFactory(new PropertyValueFactory<Binnacle, String>("objectBin"));
        changeTypeBin.setCellValueFactory(new PropertyValueFactory<Binnacle, String>("changeTypeBin"));
        descriptionBin.setCellValueFactory(new PropertyValueFactory<Binnacle, String>("descriptionBin"));
        
        datefilter.setValue(LocalDate.of(2024,1,1));


        // Populate the table with data from the DB        
		try {
			ObservableList<Binnacle> binnacleList = DB.BinnacleDB.getBinnacleListByTypeAndCountry(LocalDate.of(2024,1,1));
			binTableView.setItems(binnacleList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
        
    }
    
    @FXML
    void filter(ActionEvent event) throws SQLException {
    	ObservableList<Binnacle> binnacleList = DB.BinnacleDB.getBinnacleListByTypeAndCountry(LocalDate.of(2024,1,1));
		binTableView.setItems(binnacleList);
    }
}
