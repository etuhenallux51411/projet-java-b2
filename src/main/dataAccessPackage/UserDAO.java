package main.dataAccessPackage;

import main.exceptionPackage.*;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;

import java.sql.Date;
import java.util.List;

public interface UserDAO {
    public void createUser(UserModel user) throws UserCreationException;
    public void updateUser(UserModel user) throws UpdateUserException ;
    public Boolean deleteUser(UserModel user) throws UserDeletionException;
    public List<UserModel> getAllUsers() throws UserSearchException;
    public UserModel getUser(int id) throws UserSearchException;
    public List<LocalityModel> getLocality(String countryName) throws LocalityException;
    public List<String> getColumnsNames() throws UserSearchException;
    public String getCountryNameByHome(int id) throws CountriesDAOException;
    public int nbUser() throws UserSearchException;
    public List<UserModel> getUsersByCountry(String  name) throws UserSearchException;

    public List<UserModel> getUsersByAge(Date ageDebut , Date ageEnd) throws UserSearchException;
}
