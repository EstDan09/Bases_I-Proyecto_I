package application.Controller.User;

import java.net.URL;
import java.util.ResourceBundle;
import application.Model.Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    // Sample data for demonstration
    private final ObservableList<Record> recordList = FXCollections.observableArrayList(
        new Record("Michael", "Phelps", "USA", "Swimming", "Men's Freestyle 200m", "13seconds", "2008-08-10", "Beijing"),
        new Record("Usain", "Bolt", "Jamaica", "Athletics", "Men's Sprint 100m", "10seconds", "2008-08-16", "Beijing"),
        new Record("Simone", "Biles", "USA", "Gymnastics", "Women's All-Around", "15 points", "2016-08-11", "Rio")
    );

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        // Set up the columns to display the properties of the Record class
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        sportColumn.setCellValueFactory(new PropertyValueFactory<>("sport"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        recordColumn.setCellValueFactory(new PropertyValueFactory<>("record"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        olympicColumn.setCellValueFactory(new PropertyValueFactory<>("olympic"));

        // Set the data for the TableView
        table.setItems(recordList);
		
	}
}

