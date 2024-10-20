package DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Model.Country;
import application.Model.Event;
import application.Model.Medal;
import application.Model.Nationality;
import application.Model.People;
import application.Model.Score;
import application.Model.Teams;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ScoreDB {

	public static ObservableList<Score> getScoreList() throws SQLException {
		
		ObservableList<Score> scoreList = FXCollections.observableArrayList(
				new Score(
						1, 
						new Event(1, "Evento 1", null, null, null, null, null), 
						new Teams(1, "Costa Rica Team", null, null, null), 
						"1:05", 
						null),
				new Score(
						2, 
						new Event(1, "Evento 1", null, null, null, null, null), 
						new Teams(2, "Canada Team", null, null, null), 
						"1:03", 
						new Medal(1, "Gold")));
				
		return scoreList;
	}
	
	public static void addScore(Score score) throws SQLException {
        
    }

 
	public static void updateScore(Score score) throws SQLException {

        
    }
	
	public static void deleteScore(int id) throws SQLException {

        
    }
	
	public static Score getScoreById(int id) throws SQLException {
		
    	Score score = new Score(
    			id, 
    			new Event(1, "Evento 1", null, null, null, null, null), 
    			new Teams(1, "Costa Rica Team", null, null, null), 
    			"1:05", 
    			new Medal(1, "Gold"));    	

		return score;
	}
}
