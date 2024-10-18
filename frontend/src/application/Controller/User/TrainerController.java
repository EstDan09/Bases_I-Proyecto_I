package application.Controller.User;

import java.net.URL;
import java.util.ResourceBundle;

import application.Model.Trainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TrainerController implements Initializable {
	
	int photo_height = 100;

    @FXML
    private TableColumn<Trainer, Integer> age;

    @FXML
    private ComboBox<String> countryCB;

    @FXML
    private Button filterBtn;

    @FXML
    private TableColumn<Trainer, String> flag;

    @FXML
    private TableColumn<Trainer, String> name;

    @FXML
    private TableColumn<Trainer, String> olympic;

    @FXML
    private ComboBox<String> olympicCB;

    @FXML
    private TableColumn<Trainer, ImageView> photo;

    @FXML
    private TableColumn<Trainer, String> representing;

    @FXML
    private TableColumn<Trainer, String> surname;

    @FXML
    private TableView<Trainer> table;
   

    // This should be a list of Trainer objects, not Event
    ObservableList<Trainer> trainerList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the columns with the respective properties from Trainer
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        olympic.setCellValueFactory(new PropertyValueFactory<>("olympic"));
        representing.setCellValueFactory(new PropertyValueFactory<>("representing"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        // Add some sample data
        trainerList.addAll(
            new Trainer("John", "Doe", 45, "2024 Paris", "USA", createPhoto("/resources/usain.jpg")),
            new Trainer("Anna", "Smith", 39, "2020 Tokyo", "Canada", createPhoto("/resources/usain.jpg")),
            new Trainer("Liu", "Wang", 50, "2016 Rio", "China", createPhoto("/resources/usain.jpg")),
            new Trainer("Maria", "Garcia", 42, "2024 Paris", "Spain", createPhoto("/resources/usain.jpg"))
        );

        // Set the items to the TableView
        table.setItems(trainerList);
    }
    
    private ImageView createPhoto(String imagePath) {
        ImageView imageView = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        imageView.setFitHeight(photo_height); // Set the desired height for the flag
        imageView.setPreserveRatio(true); // Preserve the aspect ratio
        return imageView;
    }
}
