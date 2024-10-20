package application.Model;

public class Event2 {
    private int id;
    private String name;
    private String category;
    private String sport;
    private String date;
    private String startingTime;
    private String participants;

    // Constructor
    public Event2(int id, String name, String category, String sport, String date, String startingTime, String participants) {
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
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
}