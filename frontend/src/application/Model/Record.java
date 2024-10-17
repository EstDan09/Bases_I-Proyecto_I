package application.Model;

public class Record {
    private String name;
    private String surname;
    private String country;
    private String sport;
    private String category;
    private String record;
    private String date;
    private String olympic;

    // Constructor
    public Record(String name, String surname, String country, String sport, String category, String record, String date, String olympic) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.sport = sport;
        this.category = category;
        this.record = record;
        this.date = date;
        this.olympic = olympic;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCountry() {
        return country;
    }

    public String getSport() {
        return sport;
    }

    public String getCategory() {
        return category;
    }

    public String getRecord() {
        return record;
    }

    public String getDate() {
        return date;
    }

    public String getOlympic() {
        return olympic;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOlympic(String olympic) {
        this.olympic = olympic;
    }
}
