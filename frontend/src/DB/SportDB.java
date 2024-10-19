package DB;

import java.sql.SQLException;

import application.Model.Sport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SportDB {
	public static ObservableList<Sport> getSportList() throws SQLException {
		ObservableList<Sport> sportList = FXCollections.observableArrayList(
				new Sport(1, "Futbol"),
				new Sport(2, "Basketball"),
				new Sport(3, "Atletismo")
				);
		return sportList;
	}
}
