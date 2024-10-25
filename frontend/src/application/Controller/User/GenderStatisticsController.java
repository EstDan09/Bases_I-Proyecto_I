package application.Controller.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

public class GenderStatisticsController implements Initializable {

	@FXML
	private BorderPane contentPanel;
	
	@FXML
	private Button filterBtn;
	
	@FXML
	private ComboBox<String> sportCB;
	
	@FXML
	private ComboBox<String> olympicCB;
	
	private void loadChart(ArrayList<PieChart.Data> arrData) {
		ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
		pieData.clear();
		pieData.addAll(arrData);
		PieChart chart = new PieChart(pieData);
		chart.setTitle("Genders Stadistics by Sport");
		chart.setLabelLineLength(50);
		chart.setLabelsVisible(true);
		chart.setStartAngle(180);
		contentPanel.setCenter(chart);
	}
	
	public void filter(ActionEvent event) {
		if (sportCB.getValue() != null) {
			// get sportCB value
			// loadChart(ConnectDB.getGenderStatistic(sportCB.getValue()) -> arrayList<PieChart.Data>());
			ArrayList<PieChart.Data> dummy = new ArrayList<>();
			loadChart(dummy);
		}
	}
	
	private void loadSports() {
		
	}
	
//	private void loadOlympics() {
//		// function to fill up olympic combobox
//		List<String> olympics = null;
//		try {
////			olympics = connectDB.getAllOlympics();
//			for (String olympic: olympics) {
//				olympicCB.getItems().add(olympic);
//			}
//		} catch (SQLException e) {
//			System.out.println("No data in sports table");
//		}  
//	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadSports();
//		loadChart(ConnectDB.getGenderStatistic("*"));
		
		ArrayList<PieChart.Data> dummy = new ArrayList<>();
		dummy.add(new PieChart.Data("Masculine 40%", 40));
		dummy.add(new PieChart.Data("Femenine 60%", 60));
		loadChart(dummy);
		
	}

}
