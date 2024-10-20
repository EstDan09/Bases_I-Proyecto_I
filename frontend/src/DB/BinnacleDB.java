package DB;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import application.Model.Binnacle;
import application.Model.Province;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BinnacleDB {
	public static ObservableList<Binnacle> getBinnacleListByTypeAndCountry(LocalDate date) throws SQLException {

		ObservableList<Binnacle> binnacle = FXCollections.observableArrayList(
				new Binnacle("1", LocalDate.now().toString(), "09:00", "mandi", "people", "delete", "some description"));
		return binnacle;	
				
	}
}
