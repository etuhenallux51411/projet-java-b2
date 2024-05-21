package main.controllerPackage;

import main.businessPackage.UserManager;
import main.dataAccessPackage.UserDAO;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LocalityException;
import main.exceptionPackage.UserCreationException;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;

import java.util.List;

public class UserController implements UserDAO {
    private UserManager userManager;

    public UserController() throws ConnectionDataAccessException {
        userManager = new UserManager();
    }

    public void createUser(UserModel user) throws UserCreationException {
        userManager.createUser(user);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userManager.getAllUsers();
    }

    @Override
    public UserModel getUser(String email) {
        return userManager.getUser(email);
    }

    @Override
    public Boolean updateUser(UserModel user) {
        return userManager.updateUser(user);
    }

    @Override
    public Boolean deleteUser(UserModel user) {
        return userManager.deleteUser(user);
    }

    @Override
    public List<LocalityModel> getLocality(String countryName) throws LocalityException {
        return userManager.getLocality(countryName);
    }
    public List<String> getColumnsNames() {
        return userManager.getColumnsNames();
    }
}
