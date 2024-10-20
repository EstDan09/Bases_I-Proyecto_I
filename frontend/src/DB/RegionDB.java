package DB;

import java.sql.SQLException;

import application.Model.Region;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RegionDB {
	public static ObservableList<Region> getRegionListByProvince(int idProvince) throws SQLException {
		ObservableList<Region> countryList = FXCollections.observableArrayList(
				new Region(1, "Curridabat"),
				new Region(2, "Desamparados"),
				new Region(3, "Pavas")
				);
		return countryList;
	}
}
