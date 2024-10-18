package application.Model;

import javafx.scene.image.ImageView;

public class Trainer {
    private String name;
    private String surname;
    private int age;
    private String olympic;
    private String representing;
//    private ImageView flag;
    private ImageView photo;
    
    // Constructor
    public Trainer(String name, String surname, int age, String olympic, String representing, ImageView photo) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.olympic = olympic;
        this.representing = representing;
        this.photo = photo;
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

	public ImageView getPhoto() {
		return photo;
	}

	public void setPhoto(ImageView photo) {
		this.photo = photo;
	}
}
