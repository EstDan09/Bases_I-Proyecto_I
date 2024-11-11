package application.Controller.User;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import application.ConnectDB;
import application.Model.CountryRanking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CountryController implements Initializable {
	public int flag_height = 15;

    @FXML
    private TableColumn<CountryRanking, String> name;

    @FXML
    private TableColumn<CountryRanking, ImageView> flag;

    @FXML
    private TableColumn<CountryRanking, Integer> bronze;

    @FXML
    private TableColumn<CountryRanking, Integer> silver;

    @FXML
    private TableColumn<CountryRanking, Integer> gold;

    @FXML
    private TableColumn<CountryRanking, Integer> total;

    @FXML
    private TableView<CountryRanking> table;
    
    @FXML
    private Button filterBtn;
    
    @FXML
    private ComboBox olympicCB;
    
    @FXML
    private ComboBox countryCB;
    

    // Sample Data for Countries
    ObservableList<CountryRanking> countryList = FXCollections.observableArrayList(
    		new CountryRanking("China", createFlagImageView("/resources/china.png"), 3, 5, 7, 15),
            new CountryRanking("Costa Rica", createFlagImageView("/resources/crc.png"), 10, 8, 12, 30)
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	System.out.println();
        // Set up the columns with the appropriate data properties
        name.setCellValueFactory(new PropertyValueFactory<CountryRanking, String>("name"));
        flag.setCellValueFactory(new PropertyValueFactory<CountryRanking, ImageView>("flag"));
        bronze.setCellValueFactory(new PropertyValueFactory<CountryRanking, Integer>("bronze"));
        silver.setCellValueFactory(new PropertyValueFactory<CountryRanking, Integer>("silver"));
        gold.setCellValueFactory(new PropertyValueFactory<CountryRanking, Integer>("gold"));
        total.setCellValueFactory(new PropertyValueFactory<CountryRanking, Integer>("total"));
        
        try {
			loadCountryRanking("all");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        // Load the sample data into the TableView
        table.setItems(countryList);
    }
    
    // Method to create ImageView with a fixed size for the flags
    private ImageView createFlagImageView(String imagePath) {
        ImageView imageView = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        imageView.setFitHeight(flag_height); // Set the desired height for the flag
        imageView.setPreserveRatio(true); // Preserve the aspect ratio
        return imageView;
    }
    
    private void loadCountryRanking(String olympicName) throws SQLException {
        List<String[]> rankingData = ConnectDB.getMedalRankingByOlympic(olympicName);
        countryList.clear();  // Clear existing data

        for (String[] data : rankingData) {
            String countryName = data[1];
            String flagPath = data[0];
            int goldCount = Integer.parseInt(data[2]);
            int silverCount = Integer.parseInt(data[3]);
            int bronzeCount = Integer.parseInt(data[4]);
            int totalCount = Integer.parseInt(data[5]);

            countryList.add(new CountryRanking(countryName, createFlagImageView(flagPath), bronzeCount, silverCount, goldCount, totalCount));
        }
        System.out.println("Country ranking data loaded successfully.");
    }
    
    public void filter(ActionEvent ev) throws SQLException {
    	System.out.println("FILTRANDO");
    	countryList.clear();
//    	loadList(ConnectDB.getCountryRanking());
    	
    }
}
