package DB;

import application.ConnectDB;
import application.Model.Category;
import application.Model.Country;
import application.Model.Event;
import application.Model.People;
import application.Model.Sport;
import application.Model.Teams;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventsDB {

	public static ObservableList<Event> getEventsList() throws SQLException {
	    Connection con = null;
	    CallableStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        // Establish the connection
	        con =  DriverManager.getConnection(ConnectDB.host, ConnectDB.uName, ConnectDB.pass);

	        // Prepare the callable statement for the stored procedure
	        stmt = con.prepareCall("{CALL get_all_events_Am()}");

	        // Execute the stored procedure
	        rs = stmt.executeQuery();

	        List<Event> eventsList = new ArrayList<>();

	        // Process the result set
	        while (rs.next()) {
	            int eventId = rs.getInt("event_id");
	            String eventName = rs.getString("event_name");
	            String sportName = rs.getString("sport_name");
	            String categoryTitle = rs.getString("category_title");
	            String eventDate = rs.getString("event_date");
	            String eventTime = rs.getString("event_time");

	            // Parse participating teams
	            String[] teamNames = rs.getString("participating_teams").split(", ");
	            List<Teams> participants = new ArrayList<>();
	            for (String teamName : teamNames) {
	                participants.add(new Teams(0, teamName, null, null, null));
	            }

	            // Create Event object and add it to the list
	            Event event = new Event(
	                    eventId,
	                    eventName,
	                    new Category(0, categoryTitle),
	                    new Sport(0, sportName),
	                    eventDate,
	                    eventTime,
	                    participants
	            );
	            eventsList.add(event);
	        }

	        return FXCollections.observableArrayList(eventsList);
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	    } finally {
	        if (rs != null) rs.close(); // Close ResultSet
	        if (stmt != null) stmt.close(); // Close CallableStatement
	        if (con != null) con.close(); // Close Connection
	    }

	    return null;
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
