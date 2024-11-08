package DB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.ConnectDB;
import application.Model.Gender;
import application.Model.People;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.OracleTypes;

public class GenderDB {
	public static ObservableList<Gender> getGenderList() throws SQLException {
		
		Connection con = null;
        CallableStatement stmt = null;
        List<Gender> genderList = new ArrayList<>();
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(ConnectDB.host, ConnectDB.uName, ConnectDB.pass);
            stmt = con.prepareCall("{CALL get_all_genders}");            
            rs = stmt.executeQuery();	         
	        while (rs.next()) {
	        	genderList.add(new Gender(rs.getInt("id_gender"), rs.getString("name")));
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return FXCollections.observableArrayList(genderList);
	}
}
	