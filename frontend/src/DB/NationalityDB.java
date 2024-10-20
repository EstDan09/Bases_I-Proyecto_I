package DB;

import java.sql.SQLException;

import application.Model.Nationality;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NationalityDB {
	public static ObservableList<Nationality> getNationalityList() throws SQLException {
		ObservableList<Nationality> nationalityList = FXCollections.observableArrayList(
				new Nationality(1, "Costa Rican"),
				new Nationality(2, "Canadan"),
				new Nationality(3, "American")
				);
		return nationalityList;
	}
}
