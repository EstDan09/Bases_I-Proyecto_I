package DB;

import java.sql.SQLException;

import application.Model.Event;
import application.Model.Medal;
import application.Model.Score;
import application.Model.Teams;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MedalDB {
public static ObservableList<Medal> getMedalList() throws SQLException {
		
		ObservableList<Medal> medalList = FXCollections.observableArrayList(
				new Medal(1, "Gold"),
				new Medal(2, "Silver"),
				new Medal(3, "Bronce")
				);
				
		return medalList;
	}
}
