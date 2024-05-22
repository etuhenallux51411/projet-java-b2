package main.dataAccessPackage;

import main.exceptionPackage.PostSearchException;
import main.modelPackage.PostModel;

import java.util.List;

public interface PostDAO {

    public List<PostModel> getsPost(int postId) throws PostSearchException;
}
