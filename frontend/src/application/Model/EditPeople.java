package application.Model;

public class EditPeople {
    private String first_name;
    private String last_name;
    private String identification;
    private String documentType;
    private String nationality;
    private String birth_date;
    private String gender;
    private String photo;
    private String country;
    private String province;
    private String region;
    private String district;
    private String phone;
    private String phone_2;
    private String email;
    private String username;
    private String password;
    private String typerole;

    // Constructor
    public EditPeople(String first_name, String last_name, String identification, String documentType, String nationality, 
                      String birth_date, String gender, String photo, String country, String province, String region, 
                      String district, String phone, String phone_2, String email, String username, String password, String typerole) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.identification = identification;
        this.documentType = documentType;
        this.nationality = nationality;
        this.birth_date = birth_date;
        this.gender = gender;
        this.photo = photo;
        this.country = country;
        this.province = province;
        this.region = region;
        this.district = district;
        this.phone = phone;
        this.phone_2 = phone_2;
        this.email = email;
        this.username = username;
        this.password = password;
        this.typerole = typerole;
    }

    // Getters and Setters
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone_2() {
        return phone_2;
    }

    public void setPhone_2(String phone_2) {
        this.phone_2 = phone_2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return typerole;
    }

    public void setType(String typerole) {
        this.typerole = typerole;
    }
}
