package DB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import application.Model.Country;
import application.Model.District;
import application.Model.DocumentType;
import application.Model.Gender;
import application.Model.Nationality;
import application.Model.People;
import application.Model.Province;
import application.Model.Region;
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
				new People(1, "John", "Doe", null, null, new Nationality(2, "USA"), null, null, null, new Country(2, "USA"), null, null, null, null, null, null, null, null, "Athlete"),
				new People(2, "Jane", "Smith", null, null, new Nationality(3, "Canada"), null, null, null, new Country(3, "Canada"), null, null, null, null, null, null, null, null, "Admin"),
				new People(3, "Frank", "Davis", null, null, new Nationality(3, "Canada"), null, null, null, new Country(3, "Canada"), null, null, null, null, null, null, null, null, "Trainer"));
		
		
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
                .filter(people -> type.equals(people.getTyperole())) // Assuming getType() method returns the type
                .collect(Collectors.toList());
		
		return FXCollections.observableArrayList(filteredList);
	}
	
	public static People getPeopleById(int id) throws SQLException {		
		return new People(
				1, 
				"John", 
				"Doe", 
				"203350456", 
				new DocumentType(2, "Passport"), 
				new Nationality(2, "USA"), 
				LocalDate.now().toString(), 
				new Gender(1, "Male"), 
				"", 
				new Country(1, "Costa Rica"), 
				new Province(1, "San Jose"), 
				new Region(1, "Curridabat"), 
				new District(1, "Curridabat"), 
				"88880000", 
				"44440000", 
				"email@host.com", 
				"myuser", 
				"1234", 
				"Athlete");
	}
	
	public static void createPeople(People newPleople) throws SQLException {		
		
	}
	
	public static void editPeople(People newPleople) throws SQLException {		
		
	}
	
	public static void deletePeople(int id) throws SQLException {		
		
	}
}