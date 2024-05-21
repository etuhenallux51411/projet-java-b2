package main.modelPackage;

import java.util.Date;

public class LikeModel {
    private int id;
    private Date date;

    private PostModel post_liked;

    private UserModel liked_by;

    public LikeModel(Date date, PostModel post_liked, UserModel liked_by) {
        setDate(date);
        setPost_liked(post_liked);
        setLiked_by(liked_by);
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public PostModel getPost_liked() {
        return post_liked;
    }

    public void setPost_liked(PostModel post_liked) {
        this.post_liked = post_liked;
    }
    public UserModel getLiked_by() {
        return liked_by;
    }
    public void setLiked_by(UserModel liked_by) {
        this.liked_by = liked_by;
    }
}
