package application.Model;

import java.util.List;

public class Teams {
    private int id;
    private String name;
    private Country country;
    private People trainer;
    private List<People> athletes;

    // Constructor
    public Teams(int id, String name, Country country, People trainer, List<People> athletes) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.trainer = trainer;
        this.athletes = athletes;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }  
    
    public Country getCountry() {
        return country;
    }

    public People getTrainer() {
        return trainer;
    }

    public List<People> getAthletes() {
        return athletes;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }  
    
    public void setCountry(Country country) {
        this.country = country;
    }

    public void setTrainer(People trainer) {
        this.trainer = trainer;
    }

    public void setAthletes(List<People> athletes) {
        this.athletes = athletes;
    }
    
    @Override
    public String toString() {
        return name;  
    }
}
