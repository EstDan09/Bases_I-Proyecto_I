package application.Controller;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RegisterController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private DatePicker birth_date;

    @FXML
    private ComboBox<String> country;

    @FXML
    private ComboBox<String> district;

    @FXML
    private ComboBox<String> documentType;

    @FXML
    private TextField email;

    @FXML
    private Label errorMsg;

    @FXML
    private TextField first_name;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField identification;

    @FXML
    private TextField last_name;

    @FXML
    private Hyperlink loginBtn;

    @FXML
    private ComboBox<String> nationality;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phone;

    @FXML
    private TextField phone_2;

    @FXML
    private Button photo;
    
    private String photo_path;

    @FXML
    private ComboBox<String> province;

    @FXML
    private ComboBox<String> region;

    @FXML
    private Button registerBtn;

    @FXML
    private TextField username;

    @FXML
    void register(ActionEvent event) {
    	
    	first_name.getText();
    	last_name.getText();
    	identification.getText();
    	documentType.getValue();
    	nationality.getValue();
    	country.getValue();
    	province.getValue();
    	region.getValue();
    	district.getValue();
    	phone.getText();
    	phone_2.getText();
    	username.getText();
    	email.getText();
    	password.getText();
    	
		System.out.println("Register");
		errorMsg.setText("ERROR: No connection with the system");
		errorMsg.setVisible(true);
    }
    
    public void choosePic() {
    	FileChooser fileChooser = new FileChooser();
    	File fileSelected = fileChooser.showOpenDialog(stage);
    	if (fileSelected != null) {
    		photo_path = fileSelected.getPath();
    	}
    	System.out.println(photo_path);
    }
	
	public void switchToLoginScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/LoginView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Olympics | Login");
		stage.setScene(scene);
		stage.show();
	}
	
}