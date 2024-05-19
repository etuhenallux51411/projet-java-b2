package main.dataAccessPackage;

import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.UserModel;

import java.sql.Connection;

public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl() throws ConnectionDataAccessException {
        connection = ConnectionDataAccess.getInstance();
    }

    @Override
    public Boolean createUser(UserModel user) throws ConnectionDataAccessException {
        return null;
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
        return null;
    }
}
