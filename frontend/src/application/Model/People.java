package application.Model;

public class People {
    private String id;
    private String name;
    private String surename;
    private String representing;
    private String olympicYear;
    private String type;

    public People(String id, String name, String surename, String representing, String olympicYear, String type) {
        this.id = id;
        this.name = name;
        this.surename = surename;
        this.representing = representing;
        this.olympicYear = olympicYear;
        this.type = type;
    }

    // Getters and Setters for each field
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getRepresenting() {
        return representing;
    }

    public void setRepresenting(String representing) {
        this.representing = representing;
    }

    public String getOlympicYear() {
        return olympicYear;
    }

    public void setOlympicYear(String olympicYear) {
        this.olympicYear = olympicYear;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
