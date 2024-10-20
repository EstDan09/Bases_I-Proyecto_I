package application.Model;

import java.util.List;

public class Event {
	private int id;
	private String name;
    private Category category;
    private Sport sport;
    private String date;
    private String startingTime;
    private List<Teams> participants;

    // Constructor
    public Event(int id, String name, Category category, Sport sport, String date, String startingTime, List<Teams> participants) {
    	this.id = id;
    	this.name = name;
        this.category = category;
        this.sport = sport;
        this.date = date;
        this.startingTime = startingTime;
        this.participants = participants;
        
    }

    // Getters
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Sport getSport() {
        return sport;
    }

    public String getDate() {
        return date;
    }

    public String getStartingTime() {
        return startingTime;
    }
    
    public List<Teams> getParticipants() {
    	return participants;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }
    
    public void setParticipants(List<Teams> participants) {
    	this.participants = participants;
    }
    
    @Override
    public String toString() {
        return name;  
    }
}

