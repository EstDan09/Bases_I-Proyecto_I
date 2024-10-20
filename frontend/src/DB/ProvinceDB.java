package DB;

import java.sql.SQLException;

import application.Model.Country;
import application.Model.Province;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProvinceDB {
	public static ObservableList<Province> getProvinceListByCountry(int idCountry) throws SQLException {
		ObservableList<Province> countryList = FXCollections.observableArrayList(
				new Province(1, "San Jose"),
				new Province(2, "Alajuela"),
				new Province(3, "Cartago")
				);
		return countryList;
	}
}
