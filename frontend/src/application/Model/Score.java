package application.Model;

public class Score {

	private int id;
    private Event event;
    private Teams team;
    private String score;
    private Medal medal;

    // Constructor
    public Score(int id, Event event, Teams team, String score, Medal medal) {
    	this.id = id;
    	this.event = event;
        this.team = team;
        this.score = score;
        this.medal = medal;
    }

    // Getters
    public int getId() {
        return id;
    }
    
    public Event getEvent() {
        return event;
    }

    public Teams getTeam() {
        return team;
    }

    public String getScore() {
        return score;
    }

    public Medal getMedal() {
        return medal;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }      

    public void setTeam(Teams team) {
        this.team = team;
    }

    public void setScores(String score) {
        this.score = score;
    }

    public void setMedals(Medal medal) {
        this.medal = medal;
    }
    
}
