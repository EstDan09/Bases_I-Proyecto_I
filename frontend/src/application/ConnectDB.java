package application;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

public class ConnectDB {
	
    public static String host = "jdbc:oracle:thin:@127.0.0.1:1521:Proyecto1BD";
	public static String uName = "AP";
	public static String pass = "donotgiveaway";
	
	
	/* Nationality Related Procedures and Functions */
	public static void registerNationality(String name) throws SQLException {
		
	    Connection con = null;
	    CallableStatement stmt = null;
        System.out.println("HOLA");
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call register_nationality(?) }");
	        stmt.setString(1, name);
	        stmt.execute();

	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	}

	public static void updateNationality(String oldName, String newName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call update_nationality(?, ?) }");
	        stmt.setString(1, oldName);
	        stmt.setString(2, newName);
	        stmt.execute();
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

	public static List<String> getAllNationalities() throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
        List<String> nationalities = new ArrayList<>();
        ResultSet rs = null;

	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{?= call get_all_nationalities }");
	        stmt.registerOutParameter(1, OracleTypes.CURSOR);
	        stmt.execute();
	        rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) {
            	nationalities.add(rs.getString("name"));
            }
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
        System.out.println(nationalities);
	    return nationalities;
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
    
    public static List<String> getAllGenders() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        List<String> genders = new ArrayList<>();
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{? = call get_all_genders }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.executeQuery();
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) {
                genders.add(rs.getString("name"));
            }
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return genders;
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

    public static List<String> getAllIdTypes() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        List<String> idTypes = new ArrayList<>();
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{?= call get_all_id_types }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
	        stmt.execute();
	        rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) {
            	idTypes.add(rs.getString("type_id"));
            }
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        System.out.println(idTypes);
        return idTypes;
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

    public static List<String> getAllCategories() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        List<String> categories = new ArrayList<>();

        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ call get_all_categories() }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR); 
            stmt.execute(); 
            rs = (ResultSet) stmt.getObject(1); 
            while (rs.next()) {
            	categories.add(rs.getString("number_phone")); 
            }
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

	public static List<String> getAllCountries() throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    List<String> countries = new ArrayList<>();
	    ResultSet rs = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_all_countries() }");
	        stmt.registerOutParameter(1, OracleTypes.CURSOR);
	        stmt.execute();
	        rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) {
            	countries.add(rs.getString("name"));
            }
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
        System.out.println(countries);
	    return countries;
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

	public static int getProvinceId(String provinceName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    int provinceId = 0;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_province_id(?) }");
	        stmt.registerOutParameter(1, Types.INTEGER);
	        stmt.setString(2, provinceName);
	        stmt.execute();
	        provinceId = stmt.getInt(1);
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return provinceId;
	}

	public static List<String> getAllProvincesByCountry(String countryName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    List<String> provinces = new ArrayList<>();
	    ResultSet rs = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_all_countries(?) }");
	        stmt.registerOutParameter(1, OracleTypes.CURSOR);
	        stmt.setString(2, countryName);
	        stmt.execute();
	        rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) {
            	provinces.add(rs.getString("name"));
            }
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return provinces;
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

	public static int getRegionId(String regionName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    int regionId = 0;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_region_id(?) }");
	        stmt.registerOutParameter(1, Types.INTEGER);
	        stmt.setString(2, regionName);
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
	public static int getDistrictId(String districtName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    int districtId = 0;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_district_id(?) }");
	        stmt.registerOutParameter(1, Types.INTEGER);
	        stmt.setString(2, districtName);
	        stmt.execute();
	        districtId = stmt.getInt(1);
	    } finally {
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return districtId;
	}
	public static List<String> getAllDistrictsByRegion(int regionId) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    List<String> regions = new ArrayList<>();
	    ResultSet rs = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_all_districts(?) }");
	        stmt.registerOutParameter(1, OracleTypes.CURSOR);
	        stmt.setInt(2, regionId);
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
	            String[] athleteData = new String[4];
	            athleteData[0] = String.valueOf(rs.getInt("id_athlete")); 
	            athleteData[1] = rs.getString("first_name"); 
	            athleteData[2] = rs.getString("last_name"); 
	            athleteData[3] = rs.getString("country"); 
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

	public static void updateAthleteTrainer(String athleteFirstName, String athleteLastName, String trainerFirstName, String trainerLastName) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call update_athlete_trainer(?, ?, ?, ?) }");
	        stmt.setString(1, athleteFirstName);
	        stmt.setString(2, athleteLastName);
	        stmt.setString(3, trainerFirstName);
	        stmt.setString(4, trainerLastName);
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

	public static int getEmailId(String email, int personId) throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    ResultSet rs = null;
	    int emailId = 0;
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ ? = call get_email_id(?, ?) }");
	        stmt.registerOutParameter(1, Types.NUMERIC);
	        stmt.setString(2, email);
	        stmt.setInt(3, personId);
	        stmt.execute();
	        emailId = stmt.getInt(1);
	    } finally {
	        if (rs != null) rs.close();
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
	    }
	    return emailId;
	}
   
	//updateEmail PENDING
	
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
	    ResultSet rs = null;
	    List<String> roles = new ArrayList<>();
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call get_all_roles(?) }");
	        stmt.registerOutParameter(1, OracleTypes.CURSOR);
	        stmt.execute();
	        rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) {
            	roles.add(rs.getString("name"));
            }
	    } finally {
	        if (rs != null) rs.close();
	        if (stmt != null) stmt.close();
	        if (con != null) con.close();
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
	public static void registerPerson(String typeGiven, String fnameGiven, String lnameGiven, String bdateGiven,
            int idnumberGiven, String genderGiven, String countryGiven,
            String nationalityGiven, String districtGiven, String idtypeGiven,
            String emailGiven, String pathGiven, long phoneNumberGiven,
            String fnTrainerGiven, String lnTrainerGiven,
            String usernameGiven, String passwordGiven, String roleGiven) throws SQLException {
		Connection con = null;
		CallableStatement stmt = null;

		try {
			con = DriverManager.getConnection(host, uName, pass);
			stmt = con.prepareCall("{ call register_person(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");

			stmt.setString(1, typeGiven);
			stmt.setString(2, fnameGiven);
			stmt.setString(3, lnameGiven);
			stmt.setString(4, bdateGiven);
			stmt.setInt(5, idnumberGiven);
			stmt.setString(6, genderGiven);
			stmt.setString(7, countryGiven);
			stmt.setString(8, nationalityGiven);
			stmt.setString(9, districtGiven);
			stmt.setString(10, idtypeGiven);
			stmt.setString(11, emailGiven);
			stmt.setString(12, pathGiven);
			stmt.setLong(13, phoneNumberGiven);
			stmt.setString(14, fnTrainerGiven);
			stmt.setString(15, lnTrainerGiven);
			stmt.setString(16, usernameGiven);
			stmt.setString(17, passwordGiven);
			stmt.setString(18, roleGiven);

			stmt.execute();
		} catch (SQLException e) {
			System.out.println("Error registering person: " + e.getMessage());
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

    public static List<String> getAllSports() throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        List<String> sports = new ArrayList<>();
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ ? = call get_all_sports() }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
	        stmt.execute();
	        rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) {
            	sports.add(rs.getString("name"));
            }
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return sports;
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

    public static List<String[]> getAllOlympicEvents(int givenOlympicId) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        ResultSet rs = null; 
        List<String[]> eventsList = new ArrayList<>(); 
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ ? = call get_all_olympic_events(?) }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR); 
            stmt.setInt(2, givenOlympicId);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) {
                String[] eventData = new String[6]; 
                eventData[0] = String.valueOf(rs.getInt("id_event")); 
                eventData[1] = rs.getString("name"); 
                eventData[2] = rs.getString("date_event"); 
                eventData[3] = rs.getString("time_event"); 
                eventData[4] = rs.getString("category_title"); 
                eventData[5] = rs.getString("category_gender");
                eventsList.add(eventData);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            if (rs != null) rs.close(); 
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return eventsList;
    }

    public static int getOlympicEventId(int givenYear) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        int olympicId = 0;
        try {
            con = DriverManager.getConnection(host, uName, pass);
            stmt = con.prepareCall("{ ? = call get_olympic_event_id(?) }");
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
            stmt = con.prepareCall("{ ? = call get_olympic_details() }");
            stmt.registerOutParameter(1, Types.REF_CURSOR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                String[] olympicData = new String[7]; // Adjust size according to the number of columns
                olympicData[0] = rs.getString("olympic_name"); // Olympic name
                olympicData[1] = String.valueOf(rs.getInt("year")); // Year
                olympicData[2] = rs.getString("host_country"); // Host country
                olympicData[3] = String.valueOf(rs.getInt("number_of_events")); // Number of events
                olympicData[4] = String.valueOf(rs.getInt("number_of_athletes")); // Number of athletes
                olympicData[5] = String.valueOf(rs.getInt("number_of_countries")); // Participating countries
                olympicData[6] = String.valueOf(rs.getInt("number_of_medals")); // Number of medals
                
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
	    try {
	        con = DriverManager.getConnection(host, uName, pass);
	        stmt = con.prepareCall("{ call login_in(?, ?, ?) }");
	        stmt.setString(1, role);
	        stmt.setString(2, username);
	        stmt.setString(3, password);
	        stmt.execute();
	    } catch (SQLException e) {
	    	if (e.getErrorCode() == -20002) {
	            System.out.println("ERROR: Nationality already registered");
	            return false;
	        } else {
	            // Print any other SQL exception that might occur
	            System.out.println("SQL Error: " + e.getMessage());
	            return false;
	        }
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            System.out.println("Error closing resources: " + e.getMessage());
	            return false;
	        }
	    }
	    return true;
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

    
    
    
    
}
