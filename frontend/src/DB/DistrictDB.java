package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.ConnectDB;
import application.Model.District;
import application.Model.Region;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DistrictDB {
	public static ObservableList<District> getDistrictListByRegion(int idRegion) throws SQLException {
		Connection con = null;
	    CallableStatement stmt = null;
	    List<District> districts = new ArrayList<>();
	    ResultSet rs = null;
	    try {
	    	con = DriverManager.getConnection(ConnectDB.host, ConnectDB.uName, ConnectDB.pass);
	        stmt = con.prepareCall("{call get_all_districts_by_region(?)}");	
	        stmt.setInt(1, idRegion);
	        rs =stmt.executeQuery();	        
	        while (rs.next()) {	            
	        	districts.add(new District(rs.getInt("ID"), rs.getString("name")));
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }        
	    return FXCollections.observableArrayList(districts);
		
	}
}
