package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.ConnectDB;
import application.Model.DocumentType;
import application.Model.Nationality;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DocumentTypeDB {
	public static ObservableList<DocumentType> getDocumentTypeList() throws SQLException {
		Connection con = null;
	    CallableStatement stmt = null;	    
	    ResultSet rs = null;

	    try {
	        // Establish the connection
	        con = DriverManager.getConnection(ConnectDB.host, ConnectDB.uName, ConnectDB.pass);

	        // Prepare the callable statement for the stored procedure
	        stmt = con.prepareCall("{CALL get_all_identification_types()}");

	        // Execute the stored procedure
	        rs = stmt.executeQuery();

	        List<DocumentType> identificationTypeList = new ArrayList<DocumentType>();
	        // Process the result set
	        while (rs.next()) {
	        	identificationTypeList.add(
	        			new DocumentType(rs.getInt("id_identification_type"), 
	        					rs.getString("name_identification_type"))
	        			);	            
	        }
	        return FXCollections.observableArrayList(identificationTypeList);
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } finally {
	        if (rs != null) rs.close(); // Close ResultSet
	        if (stmt != null) stmt.close(); // Close CallableStatement
	        if (con != null) con.close(); // Close Connection
	    }
		
	    return null;			
	}
}
