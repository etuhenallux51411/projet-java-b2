package main.dataAccessPackage;

import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.PostSearchException;
import main.exceptionPackage.UserSearchException;
import main.modelPackage.PostModel;

import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PostDAOImpl implements PostDAO {
    private final Connection connection;

    public PostDAOImpl() throws ConnectionDataAccessException {
        connection = ConnectionDataAccess.getInstance();
    }

    public List<PostModel> getPosts(int postId) throws PostSearchException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM post WHERE post_id = ?");
            preparedStatement.setInt(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<PostModel> posts = new ArrayList<>();
            while (resultSet.next()) {
                PostModel post = new PostModel();
                post.setId(resultSet.getInt("id"));
                post.setText(resultSet.getString("text"));
                post.setCreatedAt(resultSet.getDate("created_at"));
                post.setUser(resultSet.getInt("user"));
                posts.add(post);
            }
            return posts;
        } catch (Exception e) {
            throw new PostSearchException(e.getMessage());
        }
    }
}
