package application.Model;

public class Athlete {
    private String name;
    private String surname;
    private int age;
    private String olympic;
    private String representing;
    
    // Constructor
    public Athlete(String name, String surname, int age, String olympic, String representing) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.olympic = olympic;
        this.representing = representing;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getOlympic() {
        return olympic;
    }

    public String getRepresenting() {
        return representing;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setOlympic(String olympic) {
        this.olympic = olympic;
    }

    public void setRepresenting(String representing) {
        this.representing = representing;
    }
}
