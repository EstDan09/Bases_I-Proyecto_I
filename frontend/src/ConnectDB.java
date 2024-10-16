import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class ConnectDB {
	
	public static void insertCountry(int idCountry, String name) throws SQLException {
	String host = "jdbc:oracle:thin:127.0.0.1:1521:Proyecto1BD";
	String uName = "AP";
	String pass = "donotgiveaway";
	Connection con = DriverManager.getConnection(host,uName,pass);
	CallableStatement stmt = con.prepareCall("{ call insertCountry(?, ?) }");
	
	stmt.setInt(1,idCountry);
	stmt.setString(2, name);
	stmt.execute();
	
	}
}
