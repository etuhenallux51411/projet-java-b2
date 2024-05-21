package main.modelPackage;

import java.util.Date;

public class PostModel {

    private int id;

    private String text;

    private Date createdAt;

    private UserModel user;


    public PostModel(String text, UserModel user) {
        setText(text);;
        setUser(user);
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public UserModel getUser() {
        return user;
    }
    public void setUser(UserModel user) {
        this.user = user;
    }
}
