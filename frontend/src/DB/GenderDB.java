package DB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Model.People;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.OracleTypes;

public class GenderDB {
	public static ObservableList<String> getGenderList() throws SQLException {
		ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female");
		return genderList;
	}
}
	