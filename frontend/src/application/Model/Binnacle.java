package application.Model;

public class Binnacle {
    private String id;
    private String dateBin;
    private String timeBin;
    private String userBin;
    private String objectBin;
    private String changeTypeBin;
    private String descriptionBin;

    // Constructor
    public Binnacle(String id, String dateBin, String timeBin, String userBin, String objectBin, String changeTypeBin, String descriptionBin) {
        this.id = id;
        this.dateBin = dateBin;
        this.timeBin = timeBin;
        this.userBin = userBin;
        this.objectBin = objectBin;
        this.changeTypeBin = changeTypeBin;
        this.descriptionBin = descriptionBin;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getDateBin() {
        return dateBin;
    }

    public String getTimeBin() {
        return timeBin;
    }

    public String getUserBin() {
        return userBin;
    }

    public String getObjectBin() {
        return objectBin;
    }

    public String getChangeTypeBin() {
        return changeTypeBin;
    }

    public String getDescriptionBin() {
        return descriptionBin;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setDateBin(String dateBin) {
        this.dateBin = dateBin;
    }

    public void setTimeBin(String timeBin) {
        this.timeBin = timeBin;
    }

    public void setUserBin(String userBin) {
        this.userBin = userBin;
    }

    public void setObjectBin(String objectBin) {
        this.objectBin = objectBin;
    }

    public void setChangeTypeBin(String changeTypeBin) {
        this.changeTypeBin = changeTypeBin;
    }

    public void setDescriptionBin(String descriptionBin) {
        this.descriptionBin = descriptionBin;
    }
}
