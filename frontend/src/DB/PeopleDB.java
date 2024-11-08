package DB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import application.ConnectDB;
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
		Connection con = null;
	    CallableStatement stmt = null;	    
	    ResultSet rs = null;

	    try {
	        // Establish the connection
	        con = DriverManager.getConnection(ConnectDB.host, ConnectDB.uName, ConnectDB.pass);

	        // Prepare the callable statement for the stored procedure
	        stmt = con.prepareCall("{CALL get_all_people()}");

	        // Execute the stored procedure
	        rs = stmt.executeQuery();

	        List<People> peopleList = new ArrayList<People>();
	        // Process the result set
	        while (rs.next()) {
	        	peopleList.add(
	        			new People(rs.getInt("id_person"), 
	        					rs.getString("first_name"), 
	        					rs.getString("last_name"), 
	        					rs.getString("identification_number"), 
	        					null, 
	        					new Nationality(rs.getInt("nationality_id"), rs.getString("nationality_name")), 
	        					rs.getString("birth_date"), 
	        					new Gender(rs.getInt("id_gender"), null), 
	        					null, 
	        					new Country(rs.getInt("id_country_represents"), rs.getString("country_name")), 
	        					null, 
	        					null, 
	        					new District(rs.getInt("id_district"), null), 
	        					null, 
	        					null, 
	        					null, 
	        					null, 
	        					null, 
	        					rs.getString("role"), 
	        					new People(rs.getInt("trainer_id"), 
	        							rs.getString("trainer_first_name"), 
	        							rs.getString("trainer_last_name")))
	        			);	            
	        }
	        return FXCollections.observableArrayList(peopleList);
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } finally {
	        if (rs != null) rs.close(); // Close ResultSet
	        if (stmt != null) stmt.close(); // Close CallableStatement
	        if (con != null) con.close(); // Close Connection
	    }
		
	    return null;
	}
	
	public static ObservableList<People> getPeopleListByTypeAndCountry(String type, int idCountry) throws SQLException {
		
		if(type == "Trainer") {
			Connection con = null;
		    CallableStatement stmt = null;	    
		    ResultSet rs = null;

		    try {
		        // Establish the connection
		        con = DriverManager.getConnection(ConnectDB.host, ConnectDB.uName, ConnectDB.pass);

		        // Prepare the callable statement for the stored procedure
		        stmt = con.prepareCall("{CALL get_all_trainers_by_country(?)}");
		        stmt.setInt(1, idCountry);
		        // Execute the stored procedure
		        rs = stmt.executeQuery();

		        List<People> peopleList = new ArrayList<People>();
		        // Process the result set
		        while (rs.next()) {
		        	peopleList.add(
		        			new People(rs.getInt("ID"), 
    							rs.getString("FirstName"), 
    							rs.getString("LastName"))
		        			);	            
		        }
		        return FXCollections.observableArrayList(peopleList);
		    } catch (SQLException e) {
		        System.out.println("SQL Error: " + e.getMessage());
		    } finally {
		        if (rs != null) rs.close(); // Close ResultSet
		        if (stmt != null) stmt.close(); // Close CallableStatement
		        if (con != null) con.close(); // Close Connection
		    }			
		}
				
        return null;
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
				"Athlete", 
		        new People(2, "Alejandro", "Arias", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));
		
		
	}
	
	public static void createPeople(People newPleople) throws SQLException {		
		if(newPleople.getTyperole() == "Trainer") {
			Connection con = null;
		    CallableStatement stmt = null;	    
		    ResultSet rs = null;

		    try {
		        // Establish the connection
		        con = DriverManager.getConnection(ConnectDB.host, ConnectDB.uName, ConnectDB.pass);
		        con.setAutoCommit(false);
		        // Prepare the callable statement for the stored procedure
		        stmt = con.prepareCall("{CALL register_person_trainer(?,?,?,?,?,?,?,?,?,?,?,?)}");
		        stmt.setString(1, newPleople.getFirst_name());
		        stmt.setString(2, newPleople.getLast_name());
		        stmt.setString(3, newPleople.getBirth_date());
		        stmt.setString(4, newPleople.getIdentification());
		        stmt.setString(5, newPleople.getGender().getName());
		        stmt.setString(6, newPleople.getCountry().getName());
		        stmt.setString(7, newPleople.getNationality().getName());
		        stmt.setInt(8, newPleople.getDistrict().getId());
		        stmt.setString(9, newPleople.getDocumentType().getName());
		        stmt.setString(10, "");
		        stmt.setString(11, newPleople.getPhone());
		        stmt.setString(12, newPleople.getEmail());
		        // Execute the stored procedure
		        stmt.executeUpdate();
	        	con.commit();		        
		    } catch (SQLException e) {
		        System.out.println("SQL Error: " + e.getMessage());
		    } finally {
		        if (rs != null) rs.close(); // Close ResultSet
		        if (stmt != null) stmt.close(); // Close CallableStatement
		        if (con != null) con.close(); // Close Connection
		    }			
		}
		
		if(newPleople.getTyperole() == "Athlete") {
			Connection con = null;
		    CallableStatement stmt = null;	    
		    ResultSet rs = null;

		    try {
		        // Establish the connection
		        con = DriverManager.getConnection(ConnectDB.host, ConnectDB.uName, ConnectDB.pass);
		        con.setAutoCommit(false);
		        // Prepare the callable statement for the stored procedure
		        stmt = con.prepareCall("{CALL register_person_athlete(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		        stmt.setString(1, newPleople.getFirst_name());
		        stmt.setString(2, newPleople.getLast_name());
		        stmt.setString(3, newPleople.getBirth_date());
		        stmt.setString(4, newPleople.getIdentification());
		        stmt.setString(5, newPleople.getGender().getName());
		        stmt.setString(6, newPleople.getCountry().getName());
		        stmt.setString(7, newPleople.getNationality().getName());
		        stmt.setInt(8, newPleople.getDistrict().getId());
		        stmt.setString(9, newPleople.getDocumentType().getName());
		        stmt.setString(10, "");
		        stmt.setString(11, newPleople.getPhone());
		        stmt.setString(12, newPleople.getEmail());
		        stmt.setInt(13, newPleople.getTrainer().getId());
		        // Execute the stored procedure
		        stmt.executeUpdate();
	        	con.commit();		        
		    } catch (SQLException e) {
		        System.out.println("SQL Error: " + e.getMessage());
		    } finally {
		        if (rs != null) rs.close(); // Close ResultSet
		        if (stmt != null) stmt.close(); // Close CallableStatement
		        if (con != null) con.close(); // Close Connection
		    }			
		}
		
		if(newPleople.getTyperole() == "Admin" || newPleople.getTyperole() == "User") {
			Connection con = null;
		    CallableStatement stmt = null;	    
		    ResultSet rs = null;

		    try {
		        // Establish the connection
		        con = DriverManager.getConnection(ConnectDB.host, ConnectDB.uName, ConnectDB.pass);
		        con.setAutoCommit(false);
		        // Prepare the callable statement for the stored procedure
		        stmt = con.prepareCall("{CALL register_person_user(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		        stmt.setString(1, newPleople.getFirst_name());
		        stmt.setString(2, newPleople.getLast_name());
		        stmt.setString(3, newPleople.getBirth_date());
		        stmt.setString(4, newPleople.getIdentification());
		        stmt.setString(5, newPleople.getGender().getName());
		        stmt.setString(6, newPleople.getCountry().getName());
		        stmt.setString(7, newPleople.getNationality().getName());
		        stmt.setInt(8, newPleople.getDistrict().getId());
		        stmt.setString(9, newPleople.getDocumentType().getName());
		        stmt.setString(10, "");
		        stmt.setString(11, newPleople.getPhone());
		        stmt.setString(12, newPleople.getEmail());
		        stmt.setString(13, newPleople.getTyperole().toLowerCase());
		        stmt.setString(14, newPleople.getUsername());
		        stmt.setString(15, newPleople.getPassword());
		        // Execute the stored procedure
		        stmt.executeUpdate();
	        	con.commit();		        
		    } catch (SQLException e) {
		        System.out.println("SQL Error: " + e.getMessage());
		    } finally {
		        if (rs != null) rs.close(); // Close ResultSet
		        if (stmt != null) stmt.close(); // Close CallableStatement
		        if (con != null) con.close(); // Close Connection
		    }			
		}
	}
	
	public static void editPeople(People newPleople) throws SQLException {		
		
	}
	
	public static void deletePeople(int id) throws SQLException {		
		
	}
}