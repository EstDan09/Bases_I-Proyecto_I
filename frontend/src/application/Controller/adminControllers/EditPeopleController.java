package application.Controller.adminControllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import application.ConnectDB;
import DB.CountryDB;
import DB.DocumentTypeDB;
import application.Model.Country;
import application.Model.District;
import application.Model.DocumentType;
import application.Model.Gender;
import application.Model.Nationality;
import application.Model.People;
import application.Model.Province;
import application.Model.Region;
import javafx.collections.ObservableList;
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

import javafx.stage.Stage;

public class EditPeopleController {
	private int id;
	
	// Method to accept a parameter id
    public void setId(int id) throws SQLException {
        this.id = id;
        if (this.id != 0) { 
    		title.setText("Edit Person");
    		People editPeople = DB.PeopleDB.getPeopleById(this.id);
    		first_name.setText(editPeople.getFirst_name());
    		last_name.setText(editPeople.getLast_name());
    		identification.setText(editPeople.getIdentification());    		
    		documentType.setValue(editPeople.getDocumentType());
    		nationality.setValue(editPeople.getNationality());
    		birth_date.setValue(LocalDate.parse(editPeople.getBirth_date()));
    		gender.setValue(editPeople.getGender());
    		country.setValue(editPeople.getCountry());
    		province.setValue(editPeople.getProvince());
    		region.setValue(editPeople.getRegion());
    		district.setValue(editPeople.getDistrict());
    		phone.setText(editPeople.getPhone());
    		phone_2.setText(editPeople.getPhone_2());
    		email.setText(editPeople.getEmail());
    		username.setText(editPeople.getUsername());
    		password.setText(editPeople.getPassword());
    		type.setValue(editPeople.getTyperole());
    		trainer.setValue(editPeople.getTrainer());
    	}
    }

    @FXML
    private TextField first_name;

    @FXML
    private TextField last_name;

    @FXML
    private TextField identification;

    @FXML
    private ComboBox<DocumentType> documentType;

    @FXML
    private ComboBox<Nationality> nationality;

    @FXML
    private DatePicker birth_date;

    @FXML
    private ComboBox<Gender> gender;

    @FXML
    private ComboBox<Country> country;

    @FXML
    private ComboBox<Province> province;

    @FXML
    private ComboBox<Region> region;

    @FXML
    private ComboBox<District> district;

    @FXML
    private TextField phone;

    @FXML
    private TextField phone_2;

    @FXML
    private TextField username;
    
    @FXML
    private Label usernameLabel;

    @FXML
    private PasswordField password;
    
    @FXML
    private Label passwordLabel;

    @FXML
    private TextField email;

    @FXML
    private Label emailLabel;
    
    @FXML
    private Button saveBtn;

    @FXML
    private Label errorMsg;

    @FXML
    private Button photo;

    @FXML
    private ComboBox<String> type;
    
    @FXML
    private ComboBox<People> trainer;

    @FXML
    private Hyperlink loginBtn;
    
    @FXML
    private Label title;

    // Initialize the combo boxes with some values
    @FXML
    public void initialize() throws SQLException {    	
        
    	ObservableList<DocumentType> documentTypeList = DocumentTypeDB.getDocumentTypeList();	 
    	documentType.setItems(documentTypeList);  
    	ObservableList<Nationality> nationalityList = DB.NationalityDB.getNationalityList();	 
    	nationality.setItems(nationalityList);        
        ObservableList<Gender> genderList = DB.GenderDB.getGenderList();	  
        gender.setItems(genderList);  
        ObservableList<Country> countryList = CountryDB.getCountryList();
        country.setItems(countryList);                           
        type.getItems().addAll("Admin", "User", "Athlete", "Trainer");
    }

    // Save button event handler
    @FXML
    private void savePerson(ActionEvent event) throws IOException, SQLException {
        errorMsg.setVisible(false);
        errorMsg.setText("");
        
        // Validate input fields
        if (first_name.getText().isEmpty() || last_name.getText().isEmpty() || identification.getText().isEmpty() || birth_date.getValue() == null) {
            errorMsg.setText("Please fill in all required fields");
            errorMsg.setVisible(true);
            return;
        }
        
        if (!first_name.getText().matches("[a-zA-Z]+")) {
            errorMsg.setText("The name must not contain numbers or symbols.");
            errorMsg.setVisible(true);
            return;
        }
        
        if (!last_name.getText().matches("[a-zA-Z]+")) {
            errorMsg.setText("The surname must not contain numbers or symbols.");
            errorMsg.setVisible(true);
            return;
        }


        if (!identification.getText().matches("^\\d{1,15}$")) {
            errorMsg.setText("The ID must be up to 15 digits and only contain numbers..");
            errorMsg.setVisible(true);
            return;
        }


        if (!email.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errorMsg.setText("Please enter a valid email address.");
            errorMsg.setVisible(true);
            return;
        }


        if (!phone.getText().matches("^\\d{1,15}$")) {
            errorMsg.setText("\"The phone number must contain up to 15 digits and not include letters or symbols.");
            errorMsg.setVisible(true);
            return;
        }



        if (birth_date.getValue().isAfter(LocalDate.now())) {
            errorMsg.setText("The date of birth cannot be in the future.");
            errorMsg.setVisible(true);
            return;
        }
 
        // Hide error message if all fields are filled
        errorMsg.setVisible(false);
        
        People currentPeople = new People(
        		0,
        		first_name.getText(),
        		last_name.getText(), 
        		identification.getText(), 
        		documentType.getValue(), 
        		nationality.getValue(), 
        		birth_date.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), 
        		gender.getValue(), 
        		"", 
        		country.getValue(), 
        		province.getValue(), 
        		region.getValue(), 
        		district.getValue(), 
        		phone.getText(), 
        		phone_2.getText(), 
        		email.getText(), 
        		username.getText(), 
        		password.getText(), 
        		type.getValue(),
                trainer.getValue());
                
        		System.out.println(type.getValue());


        //Save into DB
        if(this.id != 0) {
        	currentPeople.setId(this.id);
        	DB.PeopleDB.editPeople(currentPeople);
        }else {
        	DB.PeopleDB.createPeople(currentPeople);
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("../../Views/AdminView.fxml"));
        String title = "Admin Dashboard";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../../application.css").toExternalForm());
		stage.setTitle("Olympics | " + title);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
    }

    // Login button event handler (Switch to login scene)
    @FXML
    private void switchToLoginScene(ActionEvent event) {
        // Add code to switch to the login scene
        System.out.println("Switching to login scene");
    } 
    
    @FXML
    private void changeSelectedCountry(ActionEvent event) throws SQLException {
    	Country selectedCountry = country.getValue();
        
        ObservableList<Province> provinceList = DB.ProvinceDB.getProvinceListByCountry(selectedCountry.getId());	 
    	province.setItems(provinceList);   
    	
    	ObservableList<People> trainerList = DB.PeopleDB.getPeopleListByTypeAndCountry("Trainer", selectedCountry.getId());
        trainer.setItems(trainerList); 
    } 
    
    @FXML
    private void changeSelectedProvince(ActionEvent event) throws SQLException {
    	Province selectedProvince = province.getValue();
    	region.setItems(null);
    	if(selectedProvince != null) {
    		ObservableList<Region> regionList = DB.RegionDB.getRegionListByProvince(selectedProvince.getId());	 
        	region.setItems(regionList);
    	}
          
    } 
    
    @FXML
    private void changeSelectedRegion(ActionEvent event) throws SQLException {
    	Region selectedRegion = region.getValue();
    	district.setItems(null);
    	if(selectedRegion != null) {
	        ObservableList<District> districtList = DB.DistrictDB.getDistrictListByRegion(selectedRegion.getId());	 
	    	district.setItems(districtList); 
    	}
    } 
}