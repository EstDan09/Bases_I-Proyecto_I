package DB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Model.Gender;
import application.Model.People;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.OracleTypes;

public class GenderDB {
	public static ObservableList<Gender> getGenderList() throws SQLException {
		ObservableList<Gender> genderList = FXCollections.observableArrayList(
				new Gender(1, "Male"), 
				new Gender(2, "Female"));
		return genderList;
	}
}
	