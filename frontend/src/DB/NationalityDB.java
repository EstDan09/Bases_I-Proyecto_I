package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.ConnectDB;
import application.Model.Country;
import application.Model.District;
import application.Model.Gender;
import application.Model.Nationality;
import application.Model.People;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NationalityDB {
	public static ObservableList<Nationality> getNationalityList() throws SQLException {
		Connection con = null;
	    CallableStatement stmt = null;	    
	    ResultSet rs = null;

	    try {
	        // Establish the connection
	        con = DriverManager.getConnection(ConnectDB.host, ConnectDB.uName, ConnectDB.pass);

	        // Prepare the callable statement for the stored procedure
	        stmt = con.prepareCall("{CALL get_all_nationalities()}");

	        // Execute the stored procedure
	        rs = stmt.executeQuery();

	        List<Nationality> nationalityList = new ArrayList<Nationality>();
	        // Process the result set
	        while (rs.next()) {
	        	nationalityList.add(
	        			new Nationality(rs.getInt("id_nationality"), 
	        					rs.getString("name"))
	        			);	            
	        }
	        return FXCollections.observableArrayList(nationalityList);
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } finally {
	        if (rs != null) rs.close(); // Close ResultSet
	        if (stmt != null) stmt.close(); // Close CallableStatement
	        if (con != null) con.close(); // Close Connection
	    }
		
	    return null;
			
	}
	
	public static void editNationality(Nationality newNationality) throws SQLException {		
		
	}
}
