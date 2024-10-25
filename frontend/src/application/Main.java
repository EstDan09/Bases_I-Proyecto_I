package application;
	
import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Views/LoginView.fxml"));
			ConnectDB.getAllSports();
			/*Ejemplo segunda pantalla*/
			System.out.println(ConnectDB.getOlympicId(1974));
			ConnectDB.getAllOlympicEvents(ConnectDB.getOlympicId(1974), "0001-01-01", 0);
			
			/*Ejemnplo tercera pantalla*/
			ConnectDB.getTopScores(0, ConnectDB.getSportId("atleti"));
			
			/*Ejemplo cuarta pantalla*/
			ConnectDB.getRecords(ConnectDB.getSportId("atleti"), 0);

			
			primaryStage.setScene(new Scene(root));
						
			primaryStage.setTitle("Olympics | Login");
			
			Image icon = new Image("application/icon.png");
			
			primaryStage.getIcons().add(icon);
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
