package main.dataAccessPackage;

import main.exceptionPackage.*;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO  {
    private final Connection connection;

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
    public Boolean updateUser(UserModel user) throws UpdateUserException {
        try {
            String sql = "UPDATE user SET " +
                    "email = ?, username = ?, password = ?, date_of_birth = ?, gender = ?, " +
                    "street_and_number = ?, phone_number = ?, biography = ?, is_admin = ?, home = ? " +
                    "WHERE id = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setDate(4, new Date(user.getDateOfBirth().getTime()));
            stmt.setString(5, String.valueOf(user.getGender()));
            stmt.setString(6, user.getStreetAndNumber());

            String phoneNumber = user.getPhoneNumber();
            if (phoneNumber == null) stmt.setNull(7, Types.VARCHAR);
            else stmt.setString(7, phoneNumber);

            String bio = user.getBio();
            if (bio == null) stmt.setNull(8, Types.VARCHAR);
            else stmt.setString(8, bio);

            stmt.setBoolean(9, user.isAdmin());
            stmt.setInt(10, user.getHome());
            stmt.setInt(11, user.getId());

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new UpdateUserException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteUser(UserModel user) {
        try{
            PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            ps.setInt(1, user.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO replace
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserModel> getAllUsers() throws UserSearchException {
        List<UserModel> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(fillUser(rs));
            }
        } catch (SQLException e) {
            throw new UserSearchException(e.getMessage());
            // TODO
        }
        return users;
    }

    @Override
    public UserModel getUser(int id) throws UserSearchException {
        try {
            String sql = "SELECT * FROM user WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return fillUser(rs);
            }
        } catch (SQLException  e) {
            throw new UserSearchException("Erreur lors de la récupération de l'utilisateur");
        }
        return null;
    }

    private UserModel fillUser(ResultSet rs) throws SQLException {
        return new UserModel(
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
    }

    @Override
    public List<LocalityModel> getLocality(String countryName) throws LocalityException {
        List<LocalityModel> localities;
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
            // TODO
        }
        return columnNames;
    }

    @Override
    public String getCountryNameByHome(int userId) throws CountriesDAOException {
        try {
            String sql = "SELECT c.name FROM user u " +
                    "JOIN locality l ON u.home = l.code " +
                    "JOIN country c ON l.localisation = c.id " +
                    "WHERE u.id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
            throw new CountriesDAOException("Pays non trouvé");
        } catch (SQLException e) {
            throw new CountriesDAOException(e.getMessage());
        }
    }
}
