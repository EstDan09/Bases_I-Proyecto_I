package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	ComboBox<String> role;
	
	@FXML
	Label errorLabel;
	
	@FXML
	TextField username;
	
	@FXML
	PasswordField password;
	
	public void switchToRegisterScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../Views/RegisterView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Olympics | Register");
		stage.setScene(scene);
		stage.show();
	}
	
   public void login(ActionEvent event) throws IOException {
        String title = "";
        // CHANGE SCENE
        if (username.getText().isEmpty()) {
            errorLabel.setText("Please enter a username");
            errorLabel.setVisible(true);
        } else if (password.getText().isEmpty()) {
            errorLabel.setText("Please enter a password");
            errorLabel.setVisible(true);
        } else if (role.getValue() == null) {
            errorLabel.setText("Please choose a role");
            errorLabel.setVisible(true);
        } else {
            try {
                
                // if user is admin
            	System.out.println(username.getText());
            	if ("admin".equals(role.getValue())) {
                    if (ConnectDB.login(role.getValue(), username.getText(), password.getText())) {
                        root = FXMLLoader.load(getClass().getResource("../Views/AdminView.fxml"));
                        title = "Admin Dashboard";
                    }
                } else if ("user".equals(role.getValue())) {
                    if (ConnectDB.login(role.getValue(), username.getText(), password.getText())) {
                        root = FXMLLoader.load(getClass().getResource("../Views/UsersView.fxml"));
                    }
//                }
                }  else {
//                	root = FXMLLoader.load(getClass().getResource("../Views/AdminView.fxml"));
                root = FXMLLoader.load(getClass().getResource("../Views/UsersView.fxml"));
//                    title = "Admin Dashboard";
                }
                
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
                stage.setTitle("Olympics | " + title);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            } catch (SQLException sqlEx) {
//                ex.printStackTrace();
                errorLabel.setText("Invalid user: " + sqlEx.getMessage());
                errorLabel.setVisible(true);
                //System.out.println("Si ves este error, es porque en la clase LoginController hay problemas con los ifs de role");
            } catch (Exception ex) {
            	errorLabel.setText("Invalid user: " + ex.getMessage());
                errorLabel.setVisible(true);
            }
        }
    }
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadRoles();
	}

	private void loadRoles() {
		List<String> roles = null;
		try {
			roles = ConnectDB.getAllRoles();
			for (String r : roles) {
			    role.getItems().add(r);  // Add each typeId to the ComboBox
			}
		} catch (SQLException e) {
			System.out.println("No data in Roles table");
		}  
		role.getItems().add("abc");
		
	}
	
	
}