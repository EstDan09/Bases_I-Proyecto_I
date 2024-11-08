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
import application.Model.Province;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProvinceDB {
	public static ObservableList<Province> getProvinceListByCountry(int idCountry) throws SQLException {
		Connection con = null;
	    CallableStatement stmt = null;
	    List<Province> provinces = new ArrayList<>();
	    ResultSet rs = null;
	    try {
	    	con = DriverManager.getConnection(ConnectDB.host, ConnectDB.uName, ConnectDB.pass);
	        stmt = con.prepareCall("{call get_all_provinces_by_country(?)}");	
	        stmt.setInt(1, idCountry);
	        rs =stmt.executeQuery();	        
	        while (rs.next()) {	            
	        	provinces.add(new Province(rs.getInt("ID"), rs.getString("name")));
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }        
	    return FXCollections.observableArrayList(provinces);					
	}
}
