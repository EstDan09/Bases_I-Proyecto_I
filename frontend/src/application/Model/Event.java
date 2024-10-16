package application.Model;


public class Event {
    private String event;
    private String category;
    private String sport;
    private String date;
    private String startingTime;
    private String participants;

    // Constructor
    public Event(String event, String category, String sport, String date, String startingTime, String participants) {
        this.event = event;
        this.category = category;
        this.sport = sport;
        this.date = date;
        this.startingTime = startingTime;
        this.participants = participants;
        
    }

    // Getters
    public String getEvent() {
        return event;
    }

    public String getCategory() {
        return category;
    }

    public String getSport() {
        return sport;
    }

    public String getDate() {
        return date;
    }

    public String getStartingTime() {
        return startingTime;
    }
    
    public String getParticipants() {
    	return participants;
    }

    // Setters
    public void setEvent(String event) {
        this.event = event;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }
    
    public void setParticipants(String participants) {
    	this.participants = participants;
    }
    

}

