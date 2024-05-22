package main.dataAccessPackage;

import main.exceptionPackage.*;
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
    public void createUser(UserModel user) throws UserCreationException {
        try {
            String sql = "INSERT INTO user " +
                    "(email, username, password, date_of_birth, gender, created_at," +
                    " street_and_number, phone_number, biography, is_admin, home)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setDate(4, new Date(user.getDateOfBirth().getTime()));
            stmt.setString(5, String.valueOf(user.getGender()));
            stmt.setDate(6, new Date(System.currentTimeMillis()));
            stmt.setString(7, user.getStreetAndNumber());

            String phoneNumber = user.getPhoneNumber();
            if (phoneNumber == null) stmt.setNull(8, Types.VARCHAR);
            else stmt.setString(8, phoneNumber);

            String bio = user.getBio();
            if (bio == null) stmt.setNull(9, Types.VARCHAR);
            else stmt.setString(9, bio);

            stmt.setBoolean(10, user.isAdmin());
            stmt.setInt(11, user.getHome());

            int lines = stmt.executeUpdate();

            if (lines == 0) throw new UserCreationException("L'utilisateur n'a pas pu être créé");
        } catch (SQLException e) {
            throw new UserCreationException(e.getMessage());
        }
    }


    @Override
    public List<UserModel> getAllUsers()  {
        List<UserModel> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UserModel user = new UserModel(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getDate("date_of_birth"),
                    rs.getString("gender").charAt(0),
                    rs.getDate("created_at"),
                    rs.getString("street_and_number"),
                    rs.getString("phone_number"),
                    rs.getString("biography"),
                    rs.getBoolean("is_admin"),
                    rs.getInt("home")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public UserModel getUser(int id) throws UserResearchExecption {
        try {
            String sql = "SELECT * FROM user WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                  UserModel user = new UserModel(
                      rs.getInt("id"),
                      rs.getString("email"),
                      rs.getString("username"),
                      rs.getString("password"),
                      rs.getDate("date_of_birth"),
                         rs.getString("gender").charAt(0),
                         rs.getDate("created_at"),
                         rs.getString("street_and_number"),
                         rs.getString("phone_number"),
                         rs.getString("biography"),
                         rs.getBoolean("is_admin"),
                         rs.getInt("home")
                 );
                    return user;
            }
        } catch (SQLException  e) {
            throw new UserResearchExecption(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean updateUser(UserModel user) throws UpdateUserException {

        try {
            String sql = "update user set email = ?, username = ?, password = ?, date_of_birth = ?, gender = ?, created_at = ?, street_and_number = ?, phone_number = ?, biography = ?, is_admin = ?, home = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setDate(4, new Date(user.getDateOfBirth().getTime()));
            stmt.setString(5, String.valueOf(user.getGender()));
            stmt.setDate(6, new Date(System.currentTimeMillis()));
            stmt.setString(7, user.getStreetAndNumber());

            String phoneNumber = user.getPhoneNumber();
            if (phoneNumber == null) stmt.setNull(8, Types.VARCHAR);
            else stmt.setString(8, phoneNumber);

            String bio = user.getBio();
            if (bio == null) stmt.setNull(9, Types.VARCHAR);
            else stmt.setString(9, bio);

            stmt.setBoolean(10, user.isAdmin());
            stmt.setInt(11, user.getHome());
            stmt.setInt(12, user.getId());

             stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Boolean deleteUser(UserModel user) {
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
            String sql = "SELECT * FROM locality l " +
                    "WHERE l.localisation = (select c.id from country c where c.name = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, countryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            localities = new ArrayList<>();

            while (resultSet.next()) {
                LocalityModel locality = new LocalityModel(
                    resultSet.getInt("code"),
                    resultSet.getString("name"),
                    resultSet.getString("city"),
                    resultSet.getInt("zip_code"),
                    resultSet.getInt("localisation")
                );
                localities.add(locality);
            }
        } catch (SQLException e) {
            throw new LocalityException(e.getMessage());
        }
        return localities;
    }

    public List<String> getColumnsNames() {
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
