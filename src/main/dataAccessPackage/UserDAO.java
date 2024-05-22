package main.dataAccessPackage;

import main.exceptionPackage.LocalityException;
import main.exceptionPackage.UserCreationException;
import main.exceptionPackage.UserResearchExecption;
import main.exceptionPackage.UpdateUserException;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;

import java.util.List;

public interface UserDAO {
    public void createUser(UserModel user) throws UserCreationException;
    public List<UserModel> getAllUsers();
    public UserModel getUser(int id) throws UserResearchExecption;
    public Boolean updateUser(UserModel user) throws UpdateUserException ;
    public Boolean deleteUser(UserModel user);
    public List<LocalityModel> getLocality(String countryName) throws LocalityException;
    public List<String> getColumnsNames();
}
