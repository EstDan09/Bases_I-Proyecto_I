package application.Model;

public class Gender {
	private int id;
    private String name;
    
    public Gender(int id, String name) {
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
