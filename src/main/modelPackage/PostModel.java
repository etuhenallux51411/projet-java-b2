package main.modelPackage;

import java.util.Date;

public class PostModel {
    private int id;
    private String text;
    private Date createdAt;
    private Integer user;

    public PostModel(int id,String text, Integer user) {
        setId(id);
        setText(text);;
        setUser(user);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
