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

public class PeopleDB {
	public static ObservableList<People> getPeopleList() throws SQLException {
		/*String host = "jdbc:oracle:thin:127.0.0.1:1521:Proyecto1BD";
		String uName = "AP";
		String pass = "donotgiveaway";
		Connection con = DriverManager.getConnection(host, uName, pass);
		CallableStatement stmt = con.prepareCall("{?= call get getCountries }");
		stmt.registerOutParameter(1, OracleTypes.CURSOR);

		stmt.executeQuery();
		ResultSet r = (ResultSet) stmt.getObject(1);*/
		
		ObservableList<People> peopleList = FXCollections.observableArrayList(
				new People("1", "John", "Doe", "USA", "2024", "Athlete"),
	            new People("2", "Jane", "Smith", "Canada", "2020", "Admin"));
		
		
		/*while (r.next()) {
            // Retrieve data from ResultSet
			String id = r.getString("ID");
            String name = r.getString("Name");
            String surname = r.getString("Surname");
            String representing = r.getString("Representing");
            String olympicYear = r.getString("OlympicYear");
            String type = r.getString("Type");

            // Create a Person object and add it to the list
            People people = new People(id, name, surname, representing, olympicYear, type);
            peopleList.add(people);
        }*/
		
		return peopleList;
	}
	
	public static People getPeopleById(int id) throws SQLException {		
		return new People("1", "John", "Doe", "USA", "2024", "Athlete");
	}
	
	public static void createPeople(People newPleople) throws SQLException {		
		
	}
	
	public static void editPeople(People newPleople) throws SQLException {		
		
	}
	
	public static void deletePeople(int id) throws SQLException {		
		
	}
}