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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.OracleTypes;

public class CountryDB {
	public static ObservableList<Country> getCountryList() throws SQLException {
		Connection con = null;
	    CallableStatement stmt = null;
	    List<Country> countries = new ArrayList<>();
	    ResultSet rs = null;
	    try {
	    	con = DriverManager.getConnection(ConnectDB.host, ConnectDB.uName, ConnectDB.pass);
	        stmt = con.prepareCall("{call get_all_countries()}");	        
	        rs =stmt.executeQuery();	        
	        while (rs.next()) {	            
	            countries.add(new Country(rs.getInt("ID"), rs.getString("name")));
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }        
	    return FXCollections.observableArrayList(countries);			
	}
}
