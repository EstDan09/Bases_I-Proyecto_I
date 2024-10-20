package DB;

import java.sql.SQLException;

import application.Model.District;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DistrictDB {
	public static ObservableList<District> getDistrictListByRegion(int idRegion) throws SQLException {
		ObservableList<District> districtList = FXCollections.observableArrayList(
				new District(1, "Curridabat"),
				new District(2, "San Antonio"),
				new District(3, "San Juan")
				);
		return districtList;
	}
}
