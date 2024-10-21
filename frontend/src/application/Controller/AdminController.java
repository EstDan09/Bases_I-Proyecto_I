package application.Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    BorderPane contentPanel;

  
    public void changeToPeopleScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/Admin/PeopleView.fxml"));
        contentPanel.setCenter(root);
    }

    
    public void changeToEventview(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/Admin/EventsView.fxml"));
        contentPanel.setCenter(root);
    }

    public void changeToTeamView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/Admin/TeamsView.fxml"));
        contentPanel.setCenter(root);
    }
    public void changeToScoreView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/Admin/ScoreView.fxml"));
        contentPanel.setCenter(root);
    }
    public void changeToBinnacleView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/Admin/BinnacleView.fxml"));
        contentPanel.setCenter(root);
    }

    
    
    
    public void exitAdmin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/LoginView.fxml"));    
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root); 
        stage.setTitle("Olympics | Register");       
        stage.setScene(scene);
        stage.show();
    }
}
