package main.businessPackage;

import main.dataAccessPackage.PostDAO;
import main.dataAccessPackage.PostDAOImpl;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.PostSearchException;
import main.modelPackage.PostModel;

import java.util.List;

public class PostManager implements PostDAO {

    private PostDAO postDAO;

    public PostManager() throws ConnectionDataAccessException {
        postDAO = new PostDAOImpl();
    }

    public void setPostDAO(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public List<PostModel> getsPost(int postId) throws PostSearchException {
        return postDAO.getsPost(postId);
    }
}
