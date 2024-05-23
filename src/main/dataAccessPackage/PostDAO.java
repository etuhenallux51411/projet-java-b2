package main.dataAccessPackage;

import main.exceptionPackage.PostSearchException;
import main.modelPackage.PostModel;

import java.util.List;

public interface PostDAO {
    public List<PostModel> getPosts(int postId) throws PostSearchException;
}
