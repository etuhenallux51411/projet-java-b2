package main.businessPackage;

import main.dataAccessPackage.UserDAO;
import main.dataAccessPackage.UserDAOImpl;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LocalityException;
import main.exceptionPackage.UserCreationException;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;

import java.util.List;

public class UserManager implements UserDAO {
    private UserDAO userDAO;

    public UserManager() throws ConnectionDataAccessException {
        setUserDAO(new UserDAOImpl());
    }

    private void setUserDAO(UserDAO dao) {
        this.userDAO = dao;
    }

    public void createUser(UserModel user) throws UserCreationException {
        userDAO.createUser(user);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public UserModel getUser(String email) {
        return userDAO.getUser(email);
    }

    @Override
    public Boolean updateUser(UserModel user) {
        return userDAO.updateUser(user);
    }

    @Override
    public Boolean deleteUser(UserModel user) {
        return userDAO.deleteUser(user);
    }

    @Override
    public List<LocalityModel> getLocality(String countryName) throws LocalityException {
        return userDAO.getLocality(countryName);
    }
    public List<String> getColumnsNames() {
        return userDAO.getColumnsNames();
    }
}
