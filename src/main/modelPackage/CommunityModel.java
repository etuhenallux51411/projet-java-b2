package main.modelPackage;

public class CommunityModel {

    private int id;

    private String name;

    private UserModel creator;




    public CommunityModel(int id, String name, UserModel creator) {
        this.id = id;
        this.name = name;
        this.creator = creator;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public UserModel getCreator() {
        return creator;
    }

    public void setCreator(UserModel creator) {
        this.creator = creator;
    }
}
