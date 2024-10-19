package application.Controller.adminControllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Model.Binnacle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BinnacleViewController implements Initializable {

    @FXML
    private TableColumn<Binnacle, String> idColumn;

    @FXML
    private TableColumn<Binnacle, String> dateColumn;

    @FXML
    private TableColumn<Binnacle, String> timeColumn;

    @FXML
    private TableColumn<Binnacle, String> userColumn;

    @FXML
    private TableColumn<Binnacle, String> objectColumn;

    @FXML
    private TableColumn<Binnacle, String> changeTypeColumn;

    @FXML
    private TableColumn<Binnacle, String> descriptionColumn;

    @FXML
    private Label subtitle;

    @FXML
    private TableView<Binnacle> table;

    // Sample Data for Binnacle
    ObservableList<Binnacle> binnacleList = FXCollections.observableArrayList(
        new Binnacle("1", "2024-10-01", "10:00", "User1", "Object1", "Creation", "Created a new object."),
        new Binnacle("2", "2024-10-02", "11:30", "User2", "Object2", "Update", "Updated an existing object."),
        new Binnacle("3", "2024-10-03", "14:15", "User3", "Object3", "Deletion", "Deleted an object.")
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up the columns with the appropriate data properties
        idColumn.setCellValueFactory(new PropertyValueFactory<Binnacle, String>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Binnacle, String>("dateBin"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Binnacle, String>("timeBin"));
        userColumn.setCellValueFactory(new PropertyValueFactory<Binnacle, String>("userBin"));
        objectColumn.setCellValueFactory(new PropertyValueFactory<Binnacle, String>("objectBin"));
        changeTypeColumn.setCellValueFactory(new PropertyValueFactory<Binnacle, String>("changeTypeBin"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Binnacle, String>("descriptionBin"));

        // Load the sample data into the TableView
        table.setItems(binnacleList);
    }
}
