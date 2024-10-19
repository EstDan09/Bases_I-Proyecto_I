package application.Model;

public class District {
	private int id;
    private String name;
    
    public District(int id, String name) {
    	this.id = id;
    	this.name = name;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;  
    }
}
