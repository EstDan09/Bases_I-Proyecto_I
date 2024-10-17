package application.Model;

import javafx.scene.image.ImageView;

public class CountryRanking {
    private String name;
    private ImageView flag;
    private int bronze;
    private int silver;
    private int gold;
    private int total;

    // Constructor
    public CountryRanking(String name, ImageView flag, int bronze, int silver, int gold, int total) {
        this.name = name;
        this.flag = flag;
        this.bronze = bronze;
        this.silver = silver;
        this.gold = gold;
        this.total = total;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getFlag() {
        return flag;
    }

    public void setFlag(ImageView flag) {
        this.flag = flag;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

