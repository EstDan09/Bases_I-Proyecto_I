package application.Model;

public class People {
    private int id;
    private String name;
    private String surename;
    private String representing;
    private String olympicYear;
    private String type;
    private String first_name;
    private String last_name;
    private String identification;
    private DocumentType documentType;
    private Nationality nationality;
    private String birth_date;
    private Gender gender;
    private String photo;
    private Country country;
    private Province province;
    private Region region;
    private District district;
    private String phone;
    private String phone_2;
    private String email;
    private String username;
    private String password;
    private String typerole;

    // Constructor 
    public People(int id, String name, String surename, String representing, String olympicYear, String type, 
                  String first_name, String last_name, String identification, DocumentType documentType, Nationality nationality, 
                  String birth_date, Gender gender, String photo, Country country, Province province, Region region, 
                  District district, String phone, String phone_2, String email, String username, String password, 
                  String typerole) {
        this.id = id;
        this.name = name;
        this.surename = surename;
        this.representing = representing;
        this.olympicYear = olympicYear;
        this.type = type;
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

    // Getters y Setters

    public People() {
		// TODO Auto-generated constructor stub
	}      

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

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
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

    public String getTyperole() {
        return typerole;
    }

    public void setTyperole(String typerole) {
        this.typerole = typerole;
    }
    
    @Override
    public String toString() {
        return name + " " + surename;  
    }
}
