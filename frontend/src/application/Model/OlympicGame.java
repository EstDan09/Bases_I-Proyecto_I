package application.Model;

public class OlympicGame {
    private String name;
    private String host;
    private int year;
    private int totalParticipants;
    private int totalCountries;
    private int totalMedals;
    private int totalEvents;

    // Constructor
    public OlympicGame(String name, String host, int year, int totalParticipants, int totalCountries, int totalMedals, int totalEvents) {
        this.name = name;
        this.host = host;
        this.year = year;
        this.totalParticipants = totalParticipants;
        this.totalCountries = totalCountries;
        this.totalMedals = totalMedals;
        this.totalEvents = totalEvents;
        
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalParticipants() {
        return totalParticipants;
    }

    public void setTotalParticipants(int totalParticipants) {
        this.totalParticipants = totalParticipants;
    }

    public int getTotalCountries() {
        return totalCountries;
    }

    public void setTotalCountries(int totalCountries) {
        this.totalCountries = totalCountries;
    }

    public int getTotalMedals() {
        return totalMedals;
    }

    public void setTotalMedals(int totalMedals) {
        this.totalMedals = totalMedals;
    }
    
    public int getTotalEvents() {
    	return totalEvents;
    }
    
    public void setTotalEvents(int totalEvents) {
    	this.totalEvents = totalEvents;
    }
}

