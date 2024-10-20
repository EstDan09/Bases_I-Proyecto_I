package application.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import application.ConnectDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import oracle.sql.DATE;

public class RegisterController implements Initializable {
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
    	
        if (first_name.getText().isEmpty() ||
            last_name.getText().isEmpty() ||
            identification.getText().isEmpty() ||
            documentType.getValue() == null ||
            nationality.getValue() == null ||
            country.getValue() == null ||
            province.getValue() == null ||
            region.getValue() == null ||
            district.getValue() == null ||
            phone.getText().isEmpty() ||
            username.getText().isEmpty() ||
            email.getText().isEmpty() ||
            password.getText().isEmpty() ||
            birth_date.getValue() == null) {
                
            // Set error message for incomplete form
            errorMsg.setText("ERROR: Please fill all the required fields.");
            errorMsg.setVisible(true);
        } else {
	    	try {
	    		LocalDate selectedDate = birth_date.getValue();
	    		System.out.println(selectedDate);
	    		if (selectedDate != null) {
	                // Format the LocalDate to the desired format
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                //String date = selectedDate.format(formatter);
	            	

	             // Asignación de variables
	                String firstName = first_name.getText();
	                String lastName = last_name.getText();
	                String date = birth_date.getValue() != null ? birth_date.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : null;
	                int identificationNumber = Integer.parseInt(identification.getText());
	                String genderValue = gender.getValue();
	                String countryValue = country.getValue();
	                String nationalityValue = nationality.getValue();
	                int districtId = ConnectDB.getDistrictId(district.getValue(), region.getValue());
	                String documentTypeValue = documentType.getValue();
	                String photoPath = photo_path;
	                long phoneNumber = Long.parseLong(phone.getText());
	                String emailValue = email.getText();
	                String role = "user";
	                String usernameValue = username.getText();
	                String passwordValue = password.getText();

	                // Imprimir cada variable
	                System.out.println("First Name: " + firstName);
	                System.out.println("Last Name: " + lastName);
	                System.out.println("Date of Birth: " + date);
	                System.out.println("Identification Number: " + identificationNumber);
	                System.out.println("Gender: " + genderValue);
	                System.out.println("Country: " + countryValue);
	                System.out.println("Nationality: " + nationalityValue);
	                System.out.println("District ID: " + districtId);
	                System.out.println("Document Type: " + documentTypeValue);
	                System.out.println("Photo Path: " + photoPath);
	                System.out.println("Phone Number: " + phoneNumber);
	                System.out.println("Email: " + emailValue);
	                System.out.println("Role: " + role);
	                System.out.println("Username: " + usernameValue);
	                System.out.println("Password: " + passwordValue);

	                // Llamada a la función con las variables asignadas
	                ConnectDB.registerPersonUser(firstName, lastName, date, identificationNumber, genderValue, countryValue, 
	                    nationalityValue, districtId, documentTypeValue, photoPath, phoneNumber, emailValue, 
	                    role, usernameValue, passwordValue);

	    		}
	        } catch (SQLException ex) {
	        	ex.printStackTrace();
	        	errorMsg.setText("Its not possible to create this user");
	        	errorMsg.setVisible(true);
	        } catch (NumberFormatException n_ex) {
	        	errorMsg.setText("Please enter a valid number");
	        	errorMsg.setVisible(true);
	        }
        }
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
	
	public void countryChoosenEvent(ActionEvent event) throws SQLException {
		// function to fill up provinces from a country combobox when a country is selected
		if (country.getValue() != null) {
			try {
				province.getItems().clear();
				List<String[]> provinces = (List<String[]>) ConnectDB.getAllProvincesByCountry(country.getValue());
				for (String[] p: provinces) {
					province.getItems().add(p[0]);
				}
			} catch (SQLException e) {
				System.out.println("No data in Province table");
			}
		}
	}
	
	public void provinceChoosenEvent(ActionEvent event) throws SQLException {
		// function to fill up provinces from a country combobox when a country is selected
		if (country.getValue() != null) {
			System.out.println(country.getValue()+province.getValue());
			try {
				region.getItems().clear();
				List<String> regions = ConnectDB.getAllRegionsByProvince(ConnectDB.getProvinceId(province.getValue(), country.getValue()));
				for (String r: regions) {
					region.getItems().add(r);
				}
			} catch (SQLException e) {
				System.out.println("No data in Province table");
			}
		}
	}
	
	public void regionChoosenEvent(ActionEvent event) throws SQLException {
		// function to fill up provinces from a country combobox when a country is selected
		if (region.getValue() != null) {
			System.out.println(region.getValue()+province.getValue());
			try {
				district.getItems().clear();
				List<String[]> districts = (List<String[]>) ConnectDB.getAllDistrictsByRegion(ConnectDB.getRegionId(region.getValue(), province.getValue()));
				for (String[] r: districts) {
					district.getItems().add(r[0]);
				}
				System.out.println();
			} catch (SQLException e) {
				System.out.println("No data in Province table");
			}
		}
	}

// LOADERS
	private void loadDocumentTypes() {
		// function to load documentType combbox
		List<String[]> idTypes = null;
		try {
			idTypes = (List<String[]>) ConnectDB.getAllIdTypes();
			for (String[] type : idTypes) {
			    documentType.getItems().add(type[0]);  // Add each typeId to the ComboBox
			}
		} catch (SQLException e) {
			System.out.println("No data in DocumentType table");
		}  
	}
	
	private void loadGenders() {
		// function to load documentType combbox
		List<String[]> genders = null;
		try {
			genders = (List<String[]>) ConnectDB.getAllGenders();
			for (String[] g : genders) {
			    gender.getItems().add(g[0]);  // Add each typeId to the ComboBox
			}
		} catch (SQLException e) {
			System.out.println("No data in Gender table");
		}  
	}
	
	private void loadNationalities() {
		// function to load documentType combbox
		List<String[]> nationalities = null;
		try {
			nationalities = (List<String[]>) ConnectDB.getAllNationalities();
			for (String[] n : nationalities) {
			    nationality.getItems().add(n[0]);  // Add each typeId to the ComboBox
			}
		} catch (SQLException e) {
			System.out.println("No data in Nationality table");
		}  
	}
	
	private void loadCountries() {
		// function to load documentType combbox
		List<String[]> countries = null;
		try {
			countries = (List<String[]>) ConnectDB.getAllCountries();
			for (String[] c : countries) {
			    country.getItems().add(c[0]);  // Add each typeId to the ComboBox
			}
		} catch (SQLException e) {
			System.out.println("No data in Country table");
		}  
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadDocumentTypes();
		loadGenders();
		loadNationalities();
		loadCountries();
	}
}