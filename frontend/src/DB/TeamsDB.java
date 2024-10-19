package DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Model.Country;
import application.Model.People;
import application.Model.Teams;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeamsDB {

	public static ObservableList<Teams> getTeamList() throws SQLException {
		People trainer1 = new People();
		trainer1.setName("Trainer 1");
		trainer1.setId(1);
		
		People trainer2 = new People();
		trainer2.setName("Trainer 2");
		trainer2.setId(2);
		
		List<People> lista = new ArrayList<People>();
		lista.add(trainer1);
		lista.add(trainer2);
		
		ObservableList<Teams> teamsList = FXCollections.observableArrayList(
				new Teams(
						1, 
						"Costa Rica Team", 
						new Country(1, "Costa Rica"),
						trainer1, 
						lista),
				new Teams(
						2, 
						"USA Team", 
						new Country(2, "USA"),
						trainer2, 
						lista)
				);
		return teamsList;
	}
	
	public static void addTeam(Teams team) throws SQLException {
        /*String query = "INSERT INTO events (event, category, sport, team, date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, event.getEvent());
            pstmt.setString(2, event.getCategory());
            pstmt.setString(3, event.getSport());
            pstmt.setString(4, event.getTeam());
            pstmt.setString(5, event.getDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }*/
    }

 
	public static void updateTeam(Teams team) throws SQLException {

        /*try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, event.getEvent());
            pstmt.setString(2, event.getCategory());
            pstmt.setString(3, event.getSport());
            pstmt.setString(4, event.getTeam());
            pstmt.setString(5, event.getDate());
            pstmt.setString(6, event.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }*/
    }
	
	public static void deleteTeam(int id) throws SQLException {

        /*try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }*/
    }
	
	public static Teams getTeamById(int id) throws SQLException {
		People people1 = new People(1, "John", "Doe", "USA", "2024", "Athlete", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		People people2 = new People(2, "Jane", "Smith", "Canada", "2020", "Admin", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		People people3 = new People(3, "Frank", "Davis", "Canada", "2020", "Trainer", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		
		List<People> lista = new ArrayList<People>();
		lista.add(people1);
		lista.add(people2);
		
    	Teams team = new Teams(
    			1, 
    			"Costa Rica Team",
    			new Country(1, "Costa Rica"),
    			people3, 
    			lista);
    	

		return team;
	}
}
