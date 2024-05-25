package main.modelPackage;

public class MemberModel {

    private Integer community;

    private Integer user;

    private String username;

    private String street_and_number;


    private String location_name;


    private int zip_code;


    private String country ;


    public MemberModel(Integer community, Integer user) {
        this.community = community;
        this.user = user;
    }
    public MemberModel( String username, String street_and_number, String location_name, int zip_code, String country) {
        this.username = username;
        this.street_and_number = street_and_number;
        this.location_name = location_name;
        this.zip_code = zip_code;
        this.country = country;
    }

    public Integer getCommunity() {
        return community;
    }

    public void setCommunity(Integer community) {
        this.community = community;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStreetAndNumber() {
        return street_and_number;
    }

    public void setStreetAndNumber(String street_and_number) {
        this.street_and_number = street_and_number;
    }

    public String getLocationName() {
        return location_name;
    }

    public void setLocationName(String location_name) {
        this.location_name = location_name;
    }

    public int getZipCode() {
        return zip_code;
    }

    public void setZipCode(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
