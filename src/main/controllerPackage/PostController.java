package main.controllerPackage;

import main.businessPackage.PostManager;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.PostSearchException;
import main.modelPackage.PostModel;

import java.util.List;

public class PostController {

    private PostManager postManager;

    public PostController() throws ConnectionDataAccessException {
        postManager = new PostManager();
    }

    public List<PostModel> getsPost(int postId) throws PostSearchException {
        return postManager.getsPost(postId);
    }
}
