package DB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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
				new People(1, "John", "Doe", "USA", "2024", "Athlete", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null),
	            new People(2, "Jane", "Smith", "Canada", "2020", "Admin", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null),
	            new People(3, "Frank", "Davis", "Canada", "2020", "Trainer", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));
		
		
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
	
	public static ObservableList<People> getPeopleListByTypeAndCountry(String type, int idCountry) throws SQLException {

		// Filter the list to get only Athletes
        List<People> filteredList = getPeopleList().stream()
                .filter(people -> type.equals(people.getType())) // Assuming getType() method returns the type
                .collect(Collectors.toList());
		
		return FXCollections.observableArrayList(filteredList);
	}
	
	public static People getPeopleById(int id) throws SQLException {		
		return new People(1, "John", "Doe", "USA", "2024", "Athlete", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	
	public static void createPeople(People newPleople) throws SQLException {		
		
	}
	
	public static void editPeople(People newPleople) throws SQLException {		
		
	}
	
	public static void deletePeople(int id) throws SQLException {		
		
	}
}