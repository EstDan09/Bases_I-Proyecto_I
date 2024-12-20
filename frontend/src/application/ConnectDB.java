package application;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import application.Model.Country;
import application.Model.Nationality;
import application.Model.People;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import oracle.jdbc.OracleTypes;

public class ConnectDB {
	
    // Update your connection string for MySQL
    public static String host = "jdbc:mysql://127.0.0.1:3306/proyecto_bases1?useSSL=false&allowPublicKeyRetrieval=true"; // Use correct port and database name
    public static String uName = "AP";
    public static String pass = "donotgiveaway";
	
	
	/* Nationality Related Procedures and Functions */
	public static void registerNationality(String name) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;        
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        con.setAutoCommit(false);
	        stmt = con.prepareCall("{CALL register_nationality(?)}");	        
	        stmt.setString(1, name);
	        stmt.executeUpdate();
	        con.commit();
	        

	    } catch(Exception e) {
	        System.out.println(e.getMessage());
	    } finally {	    	
	        if (stmt != null) stmt.close(); // Close CallableStatement
	        if (con != null) con.close(); // Close Connection
	    }
	    
	}

	public static void updateNationality(String oldName, String newName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        con.setAutoCommit(false);
	        stmt = con.prepareCall("{CALL update_nationality(?, ?)}");
	        stmt.setString(1, oldName);
	        stmt.setString(2, newName);
	        stmt.executeUpdate();
	        con.commit();
	    } catch (SQLException e) {
	    	if (e.getErrorCode() == -20002) {
	            System.out.println("ERROR: Nationality already registered");
	        } else {
	            // Print any other SQL exception that might occur
	            System.out.println("SQL Error: " + e.getMessage());
	        }
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            System.out.println("Error closing resources: " + e.getMessage());
	        }
	    }
	}

	

	public static List<String[]> getAllNationalities() throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    List<String[]> nationalities = new ArrayList<>();
	    ResultSet rs = null;

	    try {
	        // Establish the connection
	        con = DriverManager.getConnection(host, uName, pass);

	        // Prepare the callable statement for the stored procedure
	        stmt = con.prepareCall("{CALL get_all_nationalities()}");

	        // Execute the stored procedure
	        rs = stmt.executeQuery();

	        // Process the result set
	        while (rs.next()) {
	            String[] nationalityData = new String[2];
	            nationalityData[0] = rs.getString("name"); // Get the name
	            nationalityData[1] = String.valueOf(rs.getInt("id_nationality")); // Get the ID as String
	            nationalities.add(nationalityData);
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } finally {
	        if (rs != null) rs.close(); // Close ResultSet
	        if (stmt != null) stmt.close(); // Close CallableStatement
	        if (con != null) con.close(); // Close Connection
	    }

	    // Print all nationalities in a formatted way
	    for (String[] nationality : nationalities) {
	        System.out.println(Arrays.toString(nationality));
	    }

	    return nationalities; // Return the list of nationalities
	}


	public static int getNationalityId(String nationalityName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    ResultSet rs = null;
	    int nationalityId = 0;

	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_nationality_id(?) }");
	        stmt.registerOutParameter(1, Types.INTEGER);
	        stmt.setString(2, nationalityName);
	        
	        stmt.execute();
	        nationalityId = stmt.getInt(1);
	    } finally {
	        if (rs != null) rs.close();
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }

	    return nationalityId;
	}
	
	public static Nationality getNationalityById(int nationalityId) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    ResultSet rs = null;	    

	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{CALL get_nationality_by_id(?) }");	        
	        stmt.setInt(1, nationalityId);
	        rs = stmt.executeQuery();
	        
	        while (rs.next()) {	        	
    			return new Nationality(rs.getInt("id_nationality"), 
    					rs.getString("name"));
	        }
	    } finally {
	        if (rs != null) rs.close();
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }

	    return null;
	}

	public static void deleteNationality(String name) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        con.setAutoCommit(false);
	        stmt = con.prepareCall("{CALL remove_nationality(?)}");
	        stmt.setString(1, name);
	        stmt.execute();
	        con.commit();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}
	
	/* Gender Related Procedures and Functions */
    public static void registerGender(String genderName) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_gender(?) }");
            stmt.setString(1, genderName);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }
    
    public static void updateGender(String oldName, String newName) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call update_gender(?, ?) }");
            stmt.setString(1, oldName);
            stmt.setString(2, newName);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }
    
    public static List<String[]> getAllGenders() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        List<String[]> genders = new ArrayList<>();
        ResultSet rs = null;

        try {
            // Establish the connection
            con = DriverManager.getConnection(host, uName, pass);

            // Prepare the callable statement for the stored procedure
            stmt = con.prepareCall("{CALL get_all_genders()}");

            // Execute the stored procedure
            rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                String[] genderData = new String[2];
                genderData[0] = rs.getString("name"); // Get gender name
                genderData[1] = String.valueOf(rs.getInt("id_gender")); // Get gender ID as String
                genders.add(genderData);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            if (rs != null) rs.close(); // Close ResultSet
            if (stmt != null) stmt.close(); // Close CallableStatement
            if (con != null) con.close(); // Close Connection
        }

        // Print all genders in a formatted way
        for (String[] gender : genders) {
            System.out.println(Arrays.toString(gender));
        }

        return genders; // Return the list of genders
    }

    public static void deleteGender(String name) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call delete_gender(?) }");
	        stmt.setString(1, name);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}
	
    /* ID Type Related Procedures and Functions */
    public static void registerIdentificationType(String name) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_identification_type(?) }");
            stmt.setString(1, name);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    public static void updateIdentificationType(String oldName, String newName) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call update_identification_type(?, ?) }");
            stmt.setString(1, oldName);
            stmt.setString(2, newName);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    public static List<String[]> getAllIdTypes() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        List<String[]> idTypes = new ArrayList<>();
        ResultSet rs = null;

        try {
            // Establish the connection
            con = DriverManager.getConnection(host, uName, pass);

            // Prepare the callable statement for the stored procedure
            stmt = con.prepareCall("{CALL get_all_identification_types()}");

            // Execute the stored procedure
            rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                String[] idData = new String[2];
                idData[0] = rs.getString("name_identification_type"); // Get the type ID
                idData[1] = String.valueOf(rs.getInt("id_identification_type")); // Get the ID as String
                idTypes.add(idData);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            if (rs != null) rs.close(); // Close ResultSet
            if (stmt != null) stmt.close(); // Close CallableStatement
            if (con != null) con.close(); // Close Connection
        }

        // Print all identification types in a formatted way
        for (String[] idType : idTypes) {
            System.out.println(Arrays.toString(idType));
        }

        return idTypes; // Return the list of identification types
    }

    public static int getIdTypeId(String idTypeName) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ ? = call get_id_type_id(?) }");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, idTypeName);
            stmt.execute();
            id = stmt.getInt(1);
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return id;
    }

    public static void deleteIdType(String name) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call delete_id_type(?) }");
	        stmt.setString(1, name);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}
	
	/* Phone Related Procedures and Functions */
    public static void registerPhone(long phoneNumber) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_phone(?) }");
            stmt.setLong(1, phoneNumber);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    public static void updatePhone(long oldPhoneNumber, long newPhoneNumber) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call update_phone(?, ?) }");
            stmt.setLong(1, oldPhoneNumber);
            stmt.setLong(2, newPhoneNumber);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }
    
    public static ObservableList<People> getPeopleList() throws SQLException {
        ObservableList<People> peopleList = FXCollections.observableArrayList();
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish the connection
            con = DriverManager.getConnection("jdbc:mysql://your_database_url", "username", "password");

            // Call the stored procedure
            stmt = con.prepareCall("{ call get_all_people() }");

            // Execute the procedure and process the result set
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Extract fields from each row
                int id = rs.getInt("id_person");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String identification = rs.getString("identification_number");
                String birthDate = rs.getString("birth_date");
                String role = rs.getString("role");
                String nationalityName = rs.getString("nationality_name");
                String countryName = rs.getString("country_name");
                String countryId = rs.getString("id_country_represents"); // Changed to String for Country ID

                // Trainer information (if available)
                People trainer = null;
                if (rs.getString("trainer_first_name") != null && rs.getString("trainer_last_name") != null) {
                    trainer = new People();
                    trainer.setFirst_name(rs.getString("trainer_first_name"));
                    trainer.setLast_name(rs.getString("trainer_last_name"));
                }

                // Create nationality and country objects if necessary
                Nationality nationality = new Nationality(rs.getInt("nationality_id"), nationalityName);
                Country country = new Country(countryId, countryName); // Updated to use String for countryId

                // Create the People object
                People person = new People(id, firstName, lastName, identification, null, nationality, birthDate, null, 
                                           null, country, null, null, null, null, null, null, null, null, role, trainer);
                peopleList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }

        return peopleList;
    }




    public static List<String> getAllPhones() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        List<String> numbers = new ArrayList<>();
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{? = call get_all_phones()}"); 
            stmt.registerOutParameter(1, OracleTypes.CURSOR); 
            stmt.execute(); // Execute the statement
            rs = (ResultSet) stmt.getObject(1); 
            while (rs.next()) {
                numbers.add(rs.getString("number_phone")); 
            }
        } finally {
            if (rs != null) rs.close(); // Close ResultSet
            if (stmt != null) stmt.close(); // Close CallableStatement
            if (con != null) con.close(); // Close Connection
        }
        return numbers;
    }

    public static int getPhoneId(long phoneNumber) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        int phoneId = 0;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ ? = call get_phone_id(?) }");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setLong(2, phoneNumber);
            stmt.execute();
            phoneId = stmt.getInt(1);
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return phoneId;
    }

    /* Category Related Procedures and Functions */
    public static void registerCategory(String title, String gender, String quantity) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_category(?, ?, ?) }");
            stmt.setString(1, title);
            stmt.setString(2, gender);
            stmt.setString(3, quantity);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    public static void updateCategory(String choice, String title, String gender, String quantity, 
            String newTitle, String newGender, String newQuantity) throws SQLException {
    	Connection con = null;
    	CallableStatement stmt = null;
    	try {
    		con = DriverManager.getConnection(host, uName, pass);
    		stmt = con.prepareCall("{ call update_category(?, ?, ?, ?, ?, ?, ?) }");
    		stmt.setString(1, choice);
    		stmt.setString(2, title);
    		stmt.setString(3, gender);
    		stmt.setString(4, quantity);
    		stmt.setString(5, newTitle);
    		stmt.setString(6, newGender);
    		stmt.setString(7, newQuantity);
    		stmt.execute();
    	} finally {
    		if (stmt != null) stmt.close();
    		if (con != null) con.close();
    	}
    }

    public static List<String[]> getAllCategories() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        List<String[]> categories = new ArrayList<>();

        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call get_all_categories() }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR); 
            stmt.execute();
	        rs = (ResultSet) stmt.getObject(1);
	        while (rs.next()) {
	            String[] categoryData = new String[4];
	            categoryData[0] = rs.getString("title"); 
	            categoryData[1] = rs.getString("gender"); 
	            categoryData[2] = rs.getString("name"); 
	            categoryData[3] = String.valueOf(rs.getInt("id_category")); 
	            categories.add(categoryData);
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return categories;
    }

    public static int getCategoryId(String title, String gender, String quantity) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        int categoryId = 0;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ ? = call get_category_id(?, ?, ?) }");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, title);
            stmt.setString(3, gender);
            stmt.setString(4, quantity);
            stmt.execute();
            categoryId = stmt.getInt(1);
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return categoryId;
    }
    
    public static void deleteCategory(String name) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call delete_category(?) }");
	        stmt.setString(1, name);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}
	
    /* Country Related Procedures and Functions */
	public static void registerCountry(String name, String pathD ) throws SQLException {

		Connection con = DriverManager.getConnection(host,uName,pass);
		CallableStatement stmt = con.prepareCall("{ call register_country(?, ?) }");
		
		stmt.setString(1, name);
		stmt.setString(2, pathD);
		stmt.execute();
	}
	
	public static void updateCountry(String choice, String givenName, String newName, String newFlag) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call update_country(?, ?, ?, ?) }");
	        stmt.setString(1, choice);
	        stmt.setString(2, givenName);
	        stmt.setString(3, newName);
	        stmt.setString(4, newFlag);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

	public static List<String[]> getAllCountries() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        List<String[]> countries = new ArrayList<>();
        ResultSet rs = null;

        try {
            // Establish the connection
            con = DriverManager.getConnection(host, uName, pass);

            // Prepare the callable statement for the stored procedure
            stmt = con.prepareCall("{CALL get_all_countries()}");

            // Execute the stored procedure
            rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                String[] countryData = new String[3];
                countryData[0] = rs.getString("name"); // Get the country name
                countryData[1] = String.valueOf(rs.getInt("ID")); // Get the ID as String
                countryData[2] = rs.getString("Path"); // Get the path
                countries.add(countryData);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            if (rs != null) rs.close(); // Close ResultSet
            if (stmt != null) stmt.close(); // Close CallableStatement
            if (con != null) con.close(); // Close Connection
        }

        // Print all countries in a formatted way
        for (String[] country : countries) {
            System.out.println(Arrays.toString(country));
        }

        return countries; // Return the list of countries
    }

	public static int getCountryId(String countryName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    int countryId = 0;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_country_id(?) }");
	        stmt.registerOutParameter(1, Types.INTEGER);
	        stmt.setString(2, countryName);
	        stmt.execute();
	        countryId = stmt.getInt(1);
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return countryId;
	}

    public static void deleteCountry(String name) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call delete_country(?) }");
	        stmt.setString(1, name);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

    /* Province Related Procedures and Functions */
	public static void registerProvince(String provinceName, String countryName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call register_province(?, ?) }");
	        stmt.setString(1, provinceName);
	        stmt.setString(2, countryName);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

	public static void updateProvince(String choice, String givenName, String newName, String newCountry) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call update_province(?, ?, ?, ?) }");
	        stmt.setString(1, choice);
	        stmt.setString(2, givenName);
	        stmt.setString(3, newName);
	        stmt.setString(4, newCountry);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

	public static int getProvinceId(String provinceName, String countryName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    int provinceId = 0;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_province_id(?, ?) }");
	        stmt.registerOutParameter(1, Types.INTEGER);
	        stmt.setString(2, provinceName);
	        stmt.setString(3, countryName);
	        stmt.execute();
	        provinceId = stmt.getInt(1);
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return provinceId;
	}

	public static void deleteProvince(String name, String countryname) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call delete_province(?, ?) }");
	        stmt.setString(1, name);
	        stmt.setString(2, countryname);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}
	
    public static List<String[]> getAllProvincesByCountry(String countryName) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        List<String[]> provinces = new ArrayList<>();
        ResultSet rs = null;

        try {
            // Establish the connection
            con = DriverManager.getConnection(host, uName, pass);

            // Prepare the callable statement for the stored procedure
            stmt = con.prepareCall("{CALL get_all_provinces()}");

            // Set the parameter for the stored procedure
//            stmt.setString(1, countryName);

            // Execute the stored procedure
            rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                String[] provData = new String[3];
                if (rs.getString("Country").equals(countryName)) {
                	provData[0] = rs.getString("Name"); // Get province name
                    provData[1] = String.valueOf(rs.getInt("ID")); // Get province ID as String
                    provData[2] = rs.getString("Country"); // Get country name
                    provinces.add(provData);
                }
                
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            if (rs != null) rs.close(); // Close ResultSet
            if (stmt != null) stmt.close(); // Close CallableStatement
            if (con != null) con.close(); // Close Connection
        }

        // Print all provinces in a formatted way
        for (String[] province : provinces) {
            System.out.println(Arrays.toString(province));
        }

        return provinces; // Return the list of provinces
    }
	
    /* Region Related Procedures and Functions */
	public static void registerRegion(String regionName, String provinceName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call register_region(?, ?) }");
	        stmt.setString(1, regionName);
	        stmt.setString(2, provinceName);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}
	
	public static void updateRegion(String choice, String givenName, String newName, String newProvince) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call update_region(?, ?, ?, ?) }");
	        stmt.setString(1, choice);
	        stmt.setString(2, givenName);
	        stmt.setString(3, newName);
	        stmt.setString(4, newProvince);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}
	public static int getRegionId(String regionName, String provinceName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    int regionId = 0;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_region_id(?, ?) }");
	        stmt.registerOutParameter(1, Types.INTEGER);
	        stmt.setString(2, regionName);
	        stmt.setString(3, provinceName);
	        stmt.execute();
	        regionId = stmt.getInt(1);
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return regionId;
	}
	public static List<String> getAllRegionsByProvince(int provinceId) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    List<String> regions = new ArrayList<>();
	    ResultSet rs = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_all_regions(?) }");
	        stmt.registerOutParameter(1, OracleTypes.CURSOR);
	        stmt.setInt(2, provinceId);
	        stmt.execute();
	        rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) {
            	regions.add(rs.getString("name"));
            }
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return regions;
	}
	public static void deleteRegion(String name, String provinceName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call delete_region(?, ?) }");
	        stmt.setString(1, name);
	        stmt.setString(2, provinceName);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}
	
	public static void registerDistrict(String districtName, String regionName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call register_district(?, ?) }");
	        stmt.setString(1, districtName);
	        stmt.setString(2, regionName);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}
	public static void updateDistrict(String choice, String givenName, String newName, String newRegion) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call update_district(?, ?, ?, ?) }");
	        stmt.setString(1, choice);
	        stmt.setString(2, givenName);
	        stmt.setString(3, newName);
	        stmt.setString(4, newRegion);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}
	public static int getDistrictId(String districtName, String regionName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    int districtId = 0;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_district_id(?, ?) }");
	        stmt.registerOutParameter(1, Types.INTEGER);
	        stmt.setString(2, districtName);
	        stmt.setString(3, regionName);
	        stmt.execute();
	        districtId = stmt.getInt(1);
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return districtId;
	}
	public static List<String[]> getAllDistrictsByRegion(int regionId) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    List<String[]> regions = new ArrayList<>();
	    ResultSet rs = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_all_districts(?) }");
	        stmt.registerOutParameter(1, OracleTypes.CURSOR);
	        stmt.setInt(2, regionId);
	        stmt.execute();
	        rs = (ResultSet) stmt.getObject(1);
	        while (rs.next()) {
	            String[] districtData = new String[2];
	            districtData[0] = rs.getString("name"); 
	            districtData[1] = String.valueOf(rs.getInt("id_district")); 
	            regions.add(districtData);
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return regions;
	}
	public static void deleteDistrict(String name, String regionName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call delete_district(?, ?) }");
	        stmt.setString(1, name);
	        stmt.setString(2, regionName);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}
	
	
	/* Trainer Related Procedures and Functions */
	public static void registerTrainer(int personId) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call register_trainer(?) }");
	        stmt.setInt(1, personId);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

	public static int getTrainerId(String trainerName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    int trainerId = 0;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_trainer_id(?) }");
	        stmt.registerOutParameter(1, Types.INTEGER);
	        stmt.setString(2, trainerName);
	        stmt.execute();
	        trainerId = stmt.getInt(1);
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return trainerId;
	}

	public static int getTrainerCompleteId(String firstName, String lastName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    int trainerId = 0;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_trainer_complete_id(?, ?) }");
	        stmt.registerOutParameter(1, Types.INTEGER);
	        stmt.setString(2, firstName);
	        stmt.setString(3, lastName);
	        stmt.execute();
	        trainerId = stmt.getInt(1);
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return trainerId;
	}

	public static List<String[]> getTrainerFilter(String choice, String country, int year) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    ResultSet rs = null;
	    List<String[]> trainersList = new ArrayList<>();

	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_trainer_filter(?, ?, ?) }");
	        stmt.registerOutParameter(1, OracleTypes.CURSOR);
	        stmt.setString(2, choice);
	        stmt.setString(3, country);
	        stmt.setInt(4, year);
	        stmt.execute();
	        rs = (ResultSet) stmt.getObject(1);
	        while (rs.next()) {
	            String[] trainerData = new String[4];
	            trainerData[0] = String.valueOf(rs.getInt("id_trainer")); 
	            trainerData[1] = rs.getString("first_name"); 
	            trainerData[2] = rs.getString("last_name"); 
	            trainerData[3] = rs.getString("country"); 
	            trainersList.add(trainerData);
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            System.out.println("Error closing resources: " + e.getMessage());
	        }
	    }
	    return trainersList;
	}

    /* Athlete Related Procedures and Functions */
	public static void registerAthlete(int personId, int trainerId) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call register_athlete(?, ?) }");
	        stmt.setInt(1, personId);
	        stmt.setInt(2, trainerId);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

	public static int getAthleteId(String firstName, String lastName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    int athleteId = 0;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_athlete_id(?, ?) }");
	        stmt.registerOutParameter(1, Types.INTEGER);
	        stmt.setString(2, firstName);
	        stmt.setString(3, lastName);
	        stmt.execute();
	        athleteId = stmt.getInt(1);
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return athleteId;
	}

	public static List<String[]> getAthletesFilter(String choice, String country, int year) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    ResultSet rs = null;
	    List<String[]> athletesList = new ArrayList<>(); // To hold each trainer's data
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_athletes_filter(?, ?, ?) }");
	        stmt.registerOutParameter(1, OracleTypes.CURSOR);
	        stmt.setString(2, choice);
	        stmt.setString(3, country);
	        stmt.setInt(4, year);
	        stmt.execute();
	        rs = (ResultSet) stmt.getObject(1);
	        while (rs.next()) {
	            String[] athleteData = new String[5];
	            athleteData[0] = String.valueOf(rs.getInt("id_athlete")); 
	            athleteData[1] = rs.getString("first_name"); 
	            athleteData[2] = rs.getString("last_name"); 
	            athleteData[3] = rs.getString("country");
	            athleteData[4] = rs.getString("country");
	            athletesList.add(athleteData);
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return athletesList;
	}

	public static void updateAthleteTrainer(int athleteId, int trainerId) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call update_athlete_trainer(?, ?) }");
	        stmt.setInt(1, athleteId);
	        stmt.setInt(2, trainerId);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

    /* Email Related Procedures and Functions */
	public static void registerEmail(String newEmail, int personId) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call register_email(?, ?) }");
	        stmt.setString(1, newEmail);
	        stmt.setInt(2, personId);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

	public static int getEmailId(String email) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    ResultSet rs = null;
	    int emailId = 0;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_email_id(?, ?) }");
	        stmt.registerOutParameter(1, Types.NUMERIC);
	        stmt.setString(2, email);
	        stmt.execute();
	        emailId = stmt.getInt(1);
	    } finally {
	        if (rs != null) rs.close();
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return emailId;
	}
   	
	/* Role Related Procedures and Functions */
	public static void registerRole(String roleName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call register_role(?) }");
	        stmt.setString(1, roleName);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

	public static void updateRole(String oldRoleName, String newRoleName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call update_role(?, ?) }");
	        stmt.setString(1, oldRoleName);
	        stmt.setString(2, newRoleName);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

	public static List<String> getAllRoles() throws SQLException {
		Connection con = null;
		CallableStatement stmt = null;
		List<String> roles = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(host, uName, pass);
			stmt = con.prepareCall("{CALL get_all_roles()}");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				String roleData = rs.getString("name");
				roles.add(roleData);
			}
		} catch (SQLException e) {
			System.out.println("SQL ERROR: " + e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (con != null) con.close();
		}
		
		for (String role: roles) {
			System.out.println(role);
		}
		
		return roles;

	}

	public static int getRoleId(String roleName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    int roleId = 0;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_role_id(?) }");
	        stmt.registerOutParameter(1, Types.NUMERIC);
	        stmt.setString(2, roleName);
	        stmt.execute();
	        roleId = stmt.getInt(1);
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return roleId;
	}

	/* User Related Procedures and Functions */
	public static void registerUser(int idGiven, String usernameGiven, String roleGiven, String passwordGiven) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call register_user(?, ?, ?, ?) }");
	        
	        stmt.setInt(1, idGiven);
	        stmt.setString(2, usernameGiven);
	        stmt.setString(3, roleGiven);
	        stmt.setString(4, passwordGiven);
	        
	        stmt.execute();
	    } catch (SQLException e) {
	        System.out.println("Error registering user: " + e.getMessage());
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

	/* Person Related Procedures and Functions */
	public static void registerPersonTrainer(String fnameGiven, String lnameGiven, String bdateGiven,
            int idnumberGiven, String genderGiven, String countryGiven,
            String nationalityGiven, int districtIdGiven, String idtypeGiven,
            String pathGiven, long phoneNumberGiven, String emailGiven) throws SQLException {
		Connection con = null;
		CallableStatement stmt = null;
		try {
			con = DriverManager.getConnection(host, uName, pass);
			stmt = con.prepareCall("{ call register_person_trainer(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
			stmt.setString(1, fnameGiven);
			stmt.setString(2, lnameGiven);
			stmt.setString(3, bdateGiven);
			stmt.setInt(4, idnumberGiven);
			stmt.setString(5, genderGiven);
			stmt.setString(6, countryGiven);
			stmt.setString(7, nationalityGiven);
			stmt.setInt(8, districtIdGiven);
			stmt.setString(9, idtypeGiven);
			stmt.setString(10, pathGiven);
			stmt.setLong(11, phoneNumberGiven);
			stmt.setString(12, emailGiven);
			stmt.execute();
			System.out.println("Trainer registered successfully.");
		} catch (SQLException e) {
			System.out.println("Error registering trainer: " + e.getMessage());
		} finally {
			if (stmt != null) stmt.close();
			if (con != null) con.close();
		}
	}
	public static void registerPersonAthlete(String fnameGiven, String lnameGiven, String bdateGiven,
            int idnumberGiven, String genderGiven, String countryGiven,
            String nationalityGiven, int districtGiven, String idtypeGiven,
            String pathGiven, long phoneNumberGiven, String emailGiven, int trainerIdGiven) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call register_person_athlete(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
	        stmt.setString(1, fnameGiven);
	        stmt.setString(2, lnameGiven);
	        stmt.setString(3, bdateGiven);
	        stmt.setInt(4, idnumberGiven);
	        stmt.setString(5, genderGiven);
	        stmt.setString(6, countryGiven);
	        stmt.setString(7, nationalityGiven);
	        stmt.setInt(8, districtGiven);
	        stmt.setString(9, idtypeGiven);
	        stmt.setString(10, pathGiven);
	        stmt.setLong(11, phoneNumberGiven);
	        stmt.setString(12, emailGiven);
	        stmt.setInt(13, trainerIdGiven);
	        stmt.execute();
	        System.out.println("Athlete registered successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error registering athlete: " + e.getMessage());
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}
	
	public static void registerPersonUser(String fnameGiven, String lnameGiven, String bdateGiven,
            int idnumberGiven, String genderGiven, String countryGiven,
            String nationalityGiven, int districtGiven, String idtypeGiven,
            String pathGiven, long phoneNumberGiven, String emailGiven,
            String roleGiven, String usernGiven, String passwGiven) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    System.out.println("entré");
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call register_person_user(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
	        stmt.setString(1, fnameGiven);
	        stmt.setString(2, lnameGiven);
	        stmt.setString(3, bdateGiven);
	        stmt.setInt(4, idnumberGiven);
	        stmt.setString(5, genderGiven);
	        stmt.setString(6, countryGiven);
	        stmt.setString(7, nationalityGiven);
	        stmt.setInt(8, districtGiven);
	        stmt.setString(9, idtypeGiven);
	        stmt.setString(10, pathGiven);
	        stmt.setLong(11, phoneNumberGiven);
	        stmt.setString(12, emailGiven);
	        stmt.setString(13, roleGiven);
	        stmt.setString(14, usernGiven);
	        stmt.setString(15, passwGiven);
	        stmt.execute();
	        System.out.println("User registered successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error registering user: " + e.getMessage());
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

	public static void updatePerson(String choice, int idnumberGiven, String newFname, String newLname,
            String newGender, String newCountry, String newDistrict) throws SQLException {
		Connection con = null;
		CallableStatement stmt = null;

		try {
			con = DriverManager.getConnection(host, uName, pass);
			stmt = con.prepareCall("{ call update_person(?, ?, ?, ?, ?, ?, ?) }");

			stmt.setString(1, choice);
			stmt.setInt(2, idnumberGiven);
			stmt.setString(3, newFname);
			stmt.setString(4, newLname);
			stmt.setString(5, newGender);
			stmt.setString(6, newCountry);
			stmt.setString(7, newDistrict);

			stmt.execute();
		} catch (SQLException e) {
			System.out.println("Error updating person: " + e.getMessage());
		} finally {
			if (stmt != null) stmt.close();
			if (con != null) con.close();
		}
	}

	public static void updateEmail(int emailId, String newEmail) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call update_email(?, ?) }");
	        stmt.setInt(1,emailId);
	        stmt.setString(2, newEmail);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

	/* Person_Nationality Related Procedures and Functions */
	public static void registerPersonNationality(int personId, int nationalityId) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_person_nationality(?, ?) }");
            stmt.setInt(1, personId);
            stmt.setInt(2, nationalityId);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }
	
	public static void updateSpecificNationality(int personId, String oldNationality, String newNationality) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call update_specific_nationality(?, ?, ?) }");
            stmt.setInt(1, personId);
            stmt.setString(2, oldNationality);
            stmt.setString(3, newNationality);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

	/* Person_Phone Related Procedures and Functions */
	public static void registerPersonPhone(int personId, int phoneId) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_person_phone(?, ?) }");
            stmt.setInt(1, personId);
            stmt.setInt(2, phoneId);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

	/* Person_ID Type Related Procedures and Functions */
	public static void registerPersonIdType(int personId, int idTypeId) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_person_id_t(?, ?) }");
            stmt.setInt(1, personId);
            stmt.setInt(2, idTypeId);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }
	
	/* Logs Related Procedures and Functions */
	public static void registerLog(String nameLog, String changeType, String username, String objectLog, String descriptionLog, int userId) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_log(?, ?, ?, ?, ?, ?) }");
            stmt.setString(1, nameLog);
            stmt.setString(2, changeType);
            stmt.setString(3, username);
            stmt.setString(4, objectLog);
            stmt.setString(5, descriptionLog);
            stmt.setInt(6, userId);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    public static void deleteLog(int logId) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call delete_log(?) }");
            stmt.setInt(1, logId);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    public static List<String[]> getLogs() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<String[]> logsList = new ArrayList<>();
        
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ ? = call get_logs() }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) {
                String[] logData = new String[8]; 
                logData[0] = String.valueOf(rs.getInt("id_log")); 
                logData[1] = rs.getDate("date_log").toString(); 
                logData[2] = rs.getString("time_log"); 
                logData[3] = rs.getString("name_log"); 
                logData[4] = rs.getString("change_type"); 
                logData[5] = rs.getString("username"); 
                logData[6] = rs.getString("object_log"); 
                logData[7] = rs.getString("description_log");
                logsList.add(logData); 
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return logsList; 
    }
    /* Sports Related Procedures and Functions */
    public static void registerSport(String givenName, String givenDescription, String givenRules) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_sport(?, ?, ?) }");
            stmt.setString(1, givenName);
            stmt.setString(2, givenDescription);
            stmt.setString(3, givenRules);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    public static void updateSport(String givenName, String newName, String newDescription, String newRules) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call update_sport(?, ?, ?, ?) }");
            stmt.setString(1, givenName);
            stmt.setString(2, newName);
            stmt.setString(3, newDescription);
            stmt.setString(4, newRules);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    public static List<String[]> getAllSports() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<String[]> sportsList = new ArrayList<>();

        try {
            // Establish the connection
            con = DriverManager.getConnection(ConnectDB.host, ConnectDB.uName, ConnectDB.pass);

            // Prepare and execute the stored procedure call
            stmt = con.prepareCall("{CALL get_all_sports()}");
            rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                String[] sportData = new String[4];
                sportData[0] = rs.getString("id_sport");            // Sport ID
                sportData[1] = rs.getString("name");                // Sport name
                sportData[2] = rs.getString("description_sport");   // Sport description
                sportData[3] = rs.getString("rules");               // Sport rules

                // Debug output for each sport data (optional)
                System.out.println("ID: " + sportData[0]);
                System.out.println("Name: " + sportData[1]);
                System.out.println("Description: " + sportData[2]);
                System.out.println("Rules: " + sportData[3]);

                sportsList.add(sportData);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();

        } finally {
            // Clean up resources
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }

        return sportsList;
    }
    
    public static int getSportId(String sportName) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        int sportId = 0;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ ? = call get_sport_id(?) }");
            stmt.registerOutParameter(1, Types.NUMERIC);
            stmt.setString(2, sportName);
            stmt.execute();
            sportId = stmt.getInt(1);
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return sportId;
    }

    public static void deleteSport(String name) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call delete_sport(?) }");
	        stmt.setString(1, name);
	        stmt.execute();
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

    /* Olympics Related Procedures and Functions */
    public static void registerOlympic(int givenYear, String givenCountry) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_olympic(?, ?) }");
            stmt.setInt(1, givenYear);
            stmt.setString(2, givenCountry);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    public static void updateOlympic(int givenYear, int newYear, String newCountry) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call update_olympic(?, ?, ?) }");
            stmt.setInt(1, givenYear);
            stmt.setInt(2, newYear);
            stmt.setString(3, newCountry);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }



    public static List<String[]> getAllOlympicEvents(String choice, String olympicName, String sportName) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<String[]> olympicEventsList = new ArrayList<>();
        
        try {
            // Establish the connection
            con = DriverManager.getConnection(host, uName, pass);

            // Prepare the stored procedure call with the updated arguments
            stmt = con.prepareCall("{ call get_event_list(?, ?, ?) }");
            stmt.setString(1, choice);               // "all", "olympic", "sport", or "both"
            stmt.setString(2, olympicName);          // Olympic name or empty string
            stmt.setString(3, sportName);            // Sport name or empty string

            // Execute the stored procedure and retrieve results
            rs = stmt.executeQuery();

            // Process the results
            while (rs.next()) {
                String[] eventData = new String[6];
                eventData[0] = rs.getString("event_name");
                eventData[1] = rs.getString("category_gender"); // New field for category gender
                eventData[2] = rs.getString("sport_name");
                eventData[3] = rs.getString("event_date");
                eventData[4] = rs.getString("event_time");
                eventData[5] = rs.getString("teams");

                // Debug output for each event data
                System.out.println("Event Name: " + eventData[0]);
                System.out.println("Category Gender: " + eventData[1]);
                System.out.println("Sport Name: " + eventData[2]);
                System.out.println("Event Date: " + eventData[3]);
                System.out.println("Event Time: " + eventData[4]);
                System.out.println("Teams: " + eventData[5]);

                olympicEventsList.add(eventData);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            // Clean up resources
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }

        return olympicEventsList;
    }


    public static int getOlympicId(int givenYear) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        int olympicId = 0;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ ? = call get_olympic_id(?) }");
            stmt.registerOutParameter(1, Types.NUMERIC);
            stmt.setInt(2, givenYear);
            stmt.execute();
            olympicId = stmt.getInt(1);
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return olympicId;
    }

    public static List<String[]> getOlympicDetails() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<String[]> olympicsList = new ArrayList<>(); 
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ ? = call get_olympic_details }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(1);
            System.out.println(rs);
            while (rs.next()) {
                System.out.println("HOLA");

                String[] olympicData = new String[7]; // Adjust size according to the number of columns
                olympicData[0] = rs.getString("olympic_name"); // Olympic name
                olympicData[1] = String.valueOf(rs.getInt("year")); // Year
                olympicData[2] = rs.getString("host_country"); // Host country
                olympicData[3] = String.valueOf(rs.getInt("number_of_events")); // Number of events
                olympicData[4] = String.valueOf(rs.getInt("number_of_athletes")); // Number of athletes
                olympicData[5] = String.valueOf(rs.getInt("number_of_countries")); // Participating countries
                olympicData[6] = String.valueOf(rs.getInt("number_of_medals")); // Number of medals
                System.out.println(olympicData);

                olympicsList.add(olympicData); // Add the array to the list
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == -20002) {
                System.out.println("ERROR: Nationality already registered");
            } else {
                // Print any other SQL exception that might occur
                System.out.println("SQL Error: " + e.getMessage());
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        System.out.println(olympicsList);

        return olympicsList;
    }

    /* Team Related Procedures and Functions */
    public static void registerTeam(String givenName, int givenTrainerId) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_team(?, ?) }");
            stmt.setString(1, givenName);
            stmt.setInt(2, givenTrainerId);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    /* Medal Related Procedures and Functions */
    public static void registerMedal(int givenId, String givenName) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_medal(?, ?) }");
            stmt.setInt(1, givenId);
            stmt.setString(2, givenName);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    /* Event_Medal Related Procedures and Functions */
    public static void registerEventMedal(String givenEvent, int givenMedal) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_event_medal(?, ?) }");
            stmt.setString(1, givenEvent);
            stmt.setInt(2, givenMedal);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    /* Event Related Procedures and Functions */
    public static void registerEvent(Date dateGiven, String timeGiven, String nameGiven, int idOlympicGiven, int sportNameGiven, int idCategoryGiven) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_event(?, ?, ?, ?, ?, ?) }");
            stmt.setDate(1, dateGiven);
            stmt.setString(2, timeGiven);
            stmt.setString(3, nameGiven);
            stmt.setInt(4, idOlympicGiven);
            stmt.setInt(5, sportNameGiven);
            stmt.setInt(6, idCategoryGiven);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    /* Athlete_Team Related Procedures and Functions */
    public static void registerAthleteIntoTeam(int idAthleteGiven, int idTeamGiven) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_athlete_into_team(?, ?) }");
            stmt.setInt(1, idAthleteGiven);
            stmt.setInt(2, idTeamGiven);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    /* Medal_Team Related Procedures and Functions */
    public static void registerMedalForTeam(int idTeamGiven, int idMedalGiven) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call register_medal_for_team(?, ?) }");
            stmt.setInt(1, idTeamGiven);
            stmt.setInt(2, idMedalGiven);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }

    /* Event_Team Related Procedures and Functions */
    public static void addTeamToEvent(int givenTeamId, int givenEventId, String givenScore, String givenRecord, int givenPosition) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call add_team_to_event(?, ?, ?, ?, ?) }");
            stmt.setInt(1, givenTeamId);
            stmt.setInt(2, givenEventId);
            stmt.setString(3, givenScore);
            stmt.setString(4, givenRecord);
            stmt.setInt(5, givenPosition);
            stmt.execute();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }
    
    public static boolean login(String role, String username, String password) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        boolean loginSuccessful = false;

        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call login_in(?, ?, ?) }");
            stmt.setString(1, role);
            stmt.setString(2, username);
            stmt.setString(3, password);

            // Execute the stored procedure
            stmt.execute();

            // Assuming successful login if no exception was raised
            loginSuccessful = true;
            System.out.println("Login successful");

        } catch (SQLException e) {
            if ("45000".equals(e.getSQLState())) {
                // Handle custom SQL exceptions based on error message
                System.out.println("ERROR: " + e.getMessage());
            } else {
                // For other SQL errors, print a generic message
                System.out.println("SQL Error: " + e.getMessage());
            }
            loginSuccessful = false;
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }

        return loginSuccessful;
    }
    
    public static List<String[]> getCountryRanking(int year) throws SQLException {
    	Connection con = null;
	    CallableStatement stmt = null;
	    ResultSet rs = null;
	    List<String[]> countries = new ArrayList<>();

	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_top_countries_by_medals(? }");
	        stmt.registerOutParameter(1, OracleTypes.CURSOR);
	        stmt.setInt(2, year);
	        stmt.execute();
	        rs = (ResultSet) stmt.getObject(1);
	        while (rs.next()) {
	            String[] countriesData = new String[5];
	            countriesData[0] = rs.getString("country"); 
	            countriesData[1] = String.valueOf(rs.getInt("gold_medals"));  
	            countriesData[2] = String.valueOf(rs.getInt("silver_medals")); 
	            countriesData[3] = String.valueOf(rs.getInt("bronze_medals")); 
	            countriesData[4] = String.valueOf(rs.getInt("total_medals")); 

	            countries.add(countriesData);
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            System.out.println("Error closing resources: " + e.getMessage());
	        }
	    }
	    return countries;
	}

    public static List<String[]> getParticipantsByFilter(String choice, String sport, String gender) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<String[]> participants = new ArrayList<>();
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ ? = call get_participants_filter(?, ?, ?) }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, choice);
            stmt.setString(3, sport);
            stmt.setString(4, gender);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) {
                String[] participantsData = new String[7];
                participantsData[0] = rs.getString("id_person");
                participantsData[1] = rs.getString("first_name");
                participantsData[2] = rs.getString("last_name");
                participantsData[3] = rs.getString("birth_date");
                participantsData[4] = rs.getString("country");
                participantsData[5] = rs.getString("sport"); 
                participantsData[6] = rs.getString("gender");  
                participants.add(participantsData);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return participants;
    }
    public static List<String[]> getParticipantsByAgeRange(String ageRange, String gender) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<String[]> participants = new ArrayList<>();
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ ? = call get_participants_by_age_range(?, ?) }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, ageRange);
            stmt.setString(3, gender);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) {
                String[] participantData = new String[7];
                participantData[0] = rs.getString("id_person");
                participantData[1] = rs.getString("first_name");
                participantData[2] = rs.getString("last_name");
                participantData[3] = rs.getString("birth_date");
                participantData[4] = rs.getString("age");
                participantData[5] = rs.getString("country");
                participantData[6] = rs.getString("gender");
                participants.add(participantData);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return participants;
    }
    public static int getGenderId(String genderName) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        int genderId = 0;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ ? = call get_gender_id(?) }");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, genderName);
            stmt.execute();
            genderId = stmt.getInt(1);
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return genderId;
    }
    public static List<String[]> getTopCountriesByMedals(int olympicYear) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<String[]> topCountries = new ArrayList<>();
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ ? = call get_top_countries_by_medals(?) }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setInt(2, olympicYear);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) {
                String[] countryData = new String[5];
                countryData[0] = rs.getString("country");  
                countryData[1] = rs.getString("gold_medals");
                countryData[2] = rs.getString("silver_medals");  
                countryData[3] = rs.getString("bronze_medals");  
                countryData[4] = rs.getString("total_medals");  
                topCountries.add(countryData);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return topCountries;
    }
    
    public static List<String[]> getTopScoresBySport(String sportName, int olympicYear) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<String[]> topScoresList = new ArrayList<>();

        try {
            // Establish the connection
            con = DriverManager.getConnection(host, uName, pass);

            // Prepare the stored procedure call
            stmt = con.prepareCall("{ call get_top_scores_by_sport(?, ?) }");
            stmt.setString(1, sportName != null && !sportName.isEmpty() ? sportName : "all");
            stmt.setInt(2, olympicYear);

            // Execute the stored procedure and retrieve the result set
            rs = stmt.executeQuery();

            // Process the results
            while (rs.next()) {
                String[] scoreData = new String[5];
                scoreData[0] = rs.getString("competitor_name"); // Competitor name
                scoreData[1] = rs.getString("sport_name");       // Sport name
                scoreData[2] = rs.getString("country_name");     // Country name
                scoreData[3] = rs.getString("score");            // Score
                scoreData[4] = rs.getString("olympic_name");     // Olympic name

                // Debug output for each score data (Optional, can be removed)
                System.out.println("Competitor: " + scoreData[0]);
                System.out.println("Sport: " + scoreData[1]);
                System.out.println("Country: " + scoreData[2]);
                System.out.println("Score: " + scoreData[3]);
                System.out.println("Olympic: " + scoreData[4]);

                topScoresList.add(scoreData);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            // Clean up resources
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }

        return topScoresList;
    }
    
    
    
    public static List<String[]> getRecords(String sportName, Integer olympicYear) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<String[]> recordList = new ArrayList<>();

        try {
            // Establish the connection
            System.out.println("Attempting to establish connection...");
            con = DriverManager.getConnection(host, uName, pass);
            System.out.println("Connection established.");

            // Prepare the stored procedure call
            stmt = con.prepareCall("{ call get_record(?, ?) }");

            // Set parameters with defaults "all" for sportName and 0 for olympicYear if they are null or empty
            String finalSportName = (sportName != null && !sportName.isEmpty()) ? sportName : "all";
            int finalOlympicYear = (olympicYear != null) ? olympicYear : 0;
            
            stmt.setString(1, finalSportName);
            stmt.setInt(2, finalOlympicYear);
            
            System.out.println("Parameters set: sportName = " + finalSportName + ", olympicYear = " + finalOlympicYear);

            // Execute the stored procedure and retrieve the result set
            rs = stmt.executeQuery();
            System.out.println("Stored procedure executed.");

            // Process the results
            while (rs.next()) {
                String[] recordData = new String[8];
                recordData[0] = rs.getString("athlete_name");      // Athlete's first name
                recordData[1] = rs.getString("last_name");         // Athlete's last name
                recordData[2] = rs.getString("country_name");      // Country name
                recordData[3] = rs.getString("sport_name");        // Sport name
                recordData[4] = rs.getString("category_title");    // Category title
                recordData[5] = rs.getString("highest_record");    // Highest record
                recordData[6] = rs.getString("event_date");        // Event date
                recordData[7] = rs.getString("olympic_name");      // Olympic name

                // Debug output for each record
                System.out.println("Record retrieved: " + String.join(", ", recordData));

                recordList.add(recordData);
            }

            System.out.println("Total records retrieved: " + recordList.size());

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            // Clean up resources
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
            System.out.println("Resources closed.");
        }

        return recordList;
    }
    
    public static List<String[]> getMedalRankingByOlympic(String olympicName) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<String[]> rankingList = new ArrayList<>();

        try {
            // Establish the connection
            con = DriverManager.getConnection(host, uName, pass);
            System.out.println("Connection established.");

            // Prepare the stored procedure call
            stmt = con.prepareCall("{ call get_medal_ranking_by_olympic(?) }");
            stmt.setString(1, olympicName != null && !olympicName.isEmpty() ? olympicName : "all");

            System.out.println("Calling stored procedure with olympicName = " + olympicName);

            // Execute the stored procedure and retrieve the result set
            rs = stmt.executeQuery();
            System.out.println("Stored procedure executed.");

            // Process the results
            while (rs.next()) {
                String[] rankingData = new String[6];
                rankingData[0] = rs.getString("flag");          // Flag photo path
                rankingData[1] = rs.getString("country");       // Country name
                rankingData[2] = rs.getString("gold_medals");   // Gold medals
                rankingData[3] = rs.getString("silver_medals"); // Silver medals
                rankingData[4] = rs.getString("bronze_medals"); // Bronze medals
                rankingData[5] = rs.getString("total_medals");  // Total medals

                // Print each entry for debugging
                System.out.println("Country: " + rankingData[1] + ", Gold: " + rankingData[2] + ", Silver: " + rankingData[3] + ", Bronze: " + rankingData[4] + ", Total: " + rankingData[5]);

                rankingList.add(rankingData);
            }

            System.out.println("Total records retrieved: " + rankingList.size());

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            // Clean up resources
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
            System.out.println("Resources closed.");
        }

        return rankingList;
    }


    
    public static List<String[]> getOlympicsSummary() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<String[]> olympicsSummaryList = new ArrayList<>();

        try {
            // Establish the connection
            con = DriverManager.getConnection(host, uName, pass);

            // Prepare and execute the stored procedure call
            stmt = con.prepareCall("{ call get_olympics_summary() }");
            rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                String[] summaryData = new String[8];
                summaryData[0] = rs.getString("id_olympic");
                summaryData[1] = rs.getString("olympic_name");
                summaryData[2] = rs.getString("year");
                summaryData[3] = rs.getString("country_name");
                summaryData[4] = rs.getString("total_participants");
                summaryData[5] = rs.getString("total_countries");
                summaryData[6] = rs.getString("total_medals");
                summaryData[7] = rs.getString("total_events");

                // Add each Olympic summary to the list
                olympicsSummaryList.add(summaryData);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            // Clean up resources
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }

        return olympicsSummaryList;
    }

    public static void main(String[] args) {
        try {
            List<String[]> olympicsSummaries = getOlympicsSummary();
            for (String[] summary : olympicsSummaries) {
                System.out.println("Olympic ID: " + summary[0]);
                System.out.println("Name: " + summary[1]);
                System.out.println("Year: " + summary[2]);
                System.out.println("Host Country: " + summary[3]);
                System.out.println("Total Participants: " + summary[4]);
                System.out.println("Total Countries: " + summary[5]);
                System.out.println("Total Medals: " + summary[6]);
                System.out.println("Total Events: " + summary[7]);
                System.out.println("---------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving Olympics summary: " + e.getMessage());
        }
    }
    
    public static List<String[]> getAllAthletesB() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<String[]> athleteList = new ArrayList<>();

        try {
            // Establecer conexión
        	con = DriverManager.getConnection(host, uName, pass);

            // Preparar y ejecutar la llamada al procedimiento almacenado
            stmt = con.prepareCall("{CALL get_all_athletesb()}");
            rs = stmt.executeQuery();

            // Procesar el resultado
            while (rs.next()) {
                String[] athleteData = new String[5];
                athleteData[0] = rs.getString("nombre");            // Nombre del atleta
                athleteData[1] = rs.getString("apellido");          // Apellido del atleta
                athleteData[2] = rs.getString("edad");              // Edad del atleta
                athleteData[3] = rs.getString("juego_olimpico");    // Juego olímpico
                athleteData[4] = rs.getString("pais_represents");   // País que representa

                // Debug: Imprimir cada registro de atleta (opcional)
                System.out.println("Nombre: " + athleteData[0] + ", Apellido: " + athleteData[1]);
                System.out.println("Edad: " + athleteData[2] + ", Juego Olímpico: " + athleteData[3]);
                System.out.println("País: " + athleteData[4]);

                athleteList.add(athleteData);
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        } finally {
            // Liberar recursos
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }

        return athleteList;
    }
    
    public static List<String[]> getAllTrainerssB() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        List<String[]> trainersList = new ArrayList<>();

        try {
            // Establecer conexión
        	con = DriverManager.getConnection(host, uName, pass);

            // Preparar y ejecutar la llamada al procedimiento almacenado
            stmt = con.prepareCall("{CALL get_all_trainersb()}");
            rs = stmt.executeQuery();

            // Procesar el resultado
            while (rs.next()) {
                String[] athleteData = new String[5];
                athleteData[0] = rs.getString("nombre");            // Nombre del atleta
                athleteData[1] = rs.getString("apellido");          // Apellido del atleta
                athleteData[2] = rs.getString("edad");              // Edad del atleta
                athleteData[3] = rs.getString("juego_olimpico");    // Juego olímpico
                athleteData[4] = rs.getString("pais_represents");   // País que representa

                // Debug: Imprimir cada registro de atleta (opcional)
                System.out.println("Nombre: " + athleteData[0] + ", Apellido: " + athleteData[1]);
                System.out.println("Edad: " + athleteData[2] + ", Juego Olímpico: " + athleteData[3]);
                System.out.println("País: " + athleteData[4]);

                trainersList.add(athleteData);
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        } finally {
            // Liberar recursos
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }

        return trainersList;
    }
    
}
