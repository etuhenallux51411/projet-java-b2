package main.dataAccessPackage;

import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LikeSearchException;
import main.modelPackage.LikeModel;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class LikeDAOimpl implements LikeDAO {

    private final Connection connection;

    private List<LikeModel> likes;

    public LikeDAOimpl() throws ConnectionDataAccessException {
        connection = ConnectionDataAccess.getInstance();
    }

    public List<LikeModel> getLikesBetween(Date startDate, Date endDate) throws LikeSearchException {
        likes = new ArrayList<>();
        try
        {
            String sql = "SELECT l.id, l.date, u.username, p.text " +
                    "FROM likes l " +
                    "JOIN user u ON l.liked_by = u.id " +
                    "JOIN post p ON l.post_liked = p.id " +
                    "WHERE l.date BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);
            ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    LikeModel like = new LikeModel();
                    like.setId(resultSet.getInt("id"));
                    like.setDate(resultSet.getDate("date"));
                    like.setUsername(resultSet.getString("username"));
                    like.setPostContent(resultSet.getString("text"));
                    likes.add(like);
                }
            return likes;
        } catch (SQLException e) {
            throw new LikeSearchException(e.getMessage());
        }
    }
}
