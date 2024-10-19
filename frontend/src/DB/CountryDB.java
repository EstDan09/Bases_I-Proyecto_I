package DB;

import java.sql.SQLException;

import application.Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CountryDB {
	public static ObservableList<Country> getCountryList() throws SQLException {
		ObservableList<Country> countryList = FXCollections.observableArrayList(
				new Country(1, "Costa Rica"),
				new Country(2, "Canada"),
				new Country(3, "USA")
				);
		return countryList;
	}
}
