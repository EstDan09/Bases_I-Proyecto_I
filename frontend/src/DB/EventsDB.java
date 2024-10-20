package DB;

import application.Model.Category;
import application.Model.Country;
import application.Model.Event;
import application.Model.People;
import application.Model.Sport;
import application.Model.Teams;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventsDB {

    public static ObservableList<Event> getEventsList() throws SQLException {
    	List<Teams> teams = new ArrayList<>();
    	teams.add(new Teams(1, "Costa Rica", new Country(1, "Costa Rica"), null, null));
    	teams.add(new Teams(2, "USA", new Country(2, "USA"), null, null));
    	
    	
        ObservableList<Event> eventsList = FXCollections.observableArrayList(
        		new Event(
        				1, 
        				"Evento 1", 
        				new Category(1, "Category 1"), 
        				new Sport(0, "Futbol"), 
        				"10/10/2025", 
        				"10:00", 
        				teams));
        /*String query = "SELECT * FROM events"; //ver cual es la tabla en la BD

        try (Connection conn = DatabaseConnection.getConnection(); // Connect
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id");
                String event = rs.getString("event");
                String category = rs.getString("category");
                String sport = rs.getString("sport");
                String team = rs.getString("team");
                String date = rs.getString("date");

                eventsList.add(new EventsAdmi(id, event, category, sport, team, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }*/
        return eventsList;
    }


    public static void addEvent(Event event) throws SQLException {
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

 
    public static void updateEvent(Event event) throws SQLException {
        String query = "UPDATE events SET event = ?, category = ?, sport = ?, team = ?, date = ? WHERE id = ?";

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

    public static void deleteEvent(int id) throws SQLException {
        String query = "DELETE FROM events WHERE id = ?";

        /*try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }*/
    }
    
    public static Event getEventById(int id) throws SQLException {
    	List<Teams> listTeams = new ArrayList<Teams>();
    	listTeams.add(new Teams(1, "Costa Rica Team", new Country(1, "Costa Rica"), null, null));
    	listTeams.add(new Teams(2, "USA Team", new Country(2, "Canada"), null, null));

		return new Event(
				1, 
				"Test Event", 
				new Category(1, null), 
				new Sport(1, null), 
				LocalDate.now().toString(), 
				"10:00", 
				listTeams);
	}
}
