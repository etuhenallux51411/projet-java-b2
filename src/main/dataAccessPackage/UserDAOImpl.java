package main.dataAccessPackage;

import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LocalityException;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO  {
    private Connection connection;
    private List<UserModel> users;
    private List<LocalityModel> localities;

    private List<String> columnNames;

    public UserDAOImpl() throws ConnectionDataAccessException {
        connection = ConnectionDataAccess.getInstance();
    }

    @Override
    public Boolean createUser(UserModel user) throws ConnectionDataAccessException {
        return null;
    }

    @Override
    public List<UserModel> getAllUsers() throws ConnectionDataAccessException {
        List<UserModel> users = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user");
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                UserModel user = new UserModel();
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setDateOfBirth(rs.getDate("date_of_birth"));
                user.setGender(rs.getCharacterStream("gender").toString().charAt(0));
                user.setStreetAndNumber(rs.getString("street_and_number"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setBio(rs.getString("bio"));
                user.setAdmin(rs.getBoolean("admin"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public UserModel getUser(String email) throws ConnectionDataAccessException {
        return null;
    }

    @Override
    public Boolean updateUser(UserModel user) throws ConnectionDataAccessException {
        return null;
    }

    @Override
    public Boolean deleteUser(UserModel user) throws ConnectionDataAccessException {
        try{
            PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE email = ?");
            ps.setString(1, user.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<LocalityModel> getLocality(String countryName) throws LocalityException {
        try {
            String sql = "SELECT l.* FROM localisation AS loc " +
                    "JOIN locality AS l on loc.locality = l.code " +
                    "JOIN country AS c on loc.country = c.id " +
                    "WHERE c.name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, countryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            localities = new ArrayList<>();

            while (resultSet.next()) {
                LocalityModel locality = new LocalityModel(
                    resultSet.getInt("code"),
                    resultSet.getString("name"),
                    resultSet.getString("city"),
                    resultSet.getInt("zip_code")
                );
                localities.add(locality);
            }
        } catch (SQLException e) {
            throw new LocalityException(e.getMessage());
        }
        return localities;
    }
    public List<String> getColumnsNames() throws ConnectionDataAccessException {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user LIMIT 1");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnNames;
    }
}
