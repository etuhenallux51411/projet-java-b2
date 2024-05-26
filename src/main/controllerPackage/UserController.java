package main.controllerPackage;

import main.businessPackage.UserManager;
import main.dataAccessPackage.UserDAO;
import main.exceptionPackage.*;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;

import java.sql.Date;
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
    public List<UserModel> getAllUsers() throws UserSearchException {
        return userManager.getAllUsers();
    }

    @Override
    public UserModel getUser(int id) throws UserSearchException {
        return userManager.getUser(id);
    }

    @Override
    public void updateUser(UserModel user) throws UpdateUserException {
        userManager.updateUser(user);
    }

    @Override
    public Boolean deleteUser(UserModel user) throws UserDeletionException {
        return userManager.deleteUser(user);
    }

    @Override
    public List<LocalityModel> getLocality(String countryName) throws LocalityException {
        return userManager.getLocality(countryName);
    }

    public List<String> getColumnsNames() throws UserSearchException {
        return userManager.getColumnsNames();
    }

    public String getCountryNameByHome(int userId) throws CountriesDAOException {
        return userManager.getCountryNameByHome(userId);
    }

    public int numbUser() throws UserSearchException {
        return userManager.numbUser();
    }

    public List<UserModel> getUsersByCountry(String name) throws UserSearchException {
        return userManager.getUsersByCountry(name);
    }

    public List<UserModel> getUsersByAge(Date ageDebut, Date ageEnd) throws UserSearchException {
        return userManager.getUsersByAge(ageDebut, ageEnd);
    }


}
