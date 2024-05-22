package main.businessPackage;

import main.dataAccessPackage.UserDAO;
import main.dataAccessPackage.UserDAOImpl;
import main.exceptionPackage.*;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;
import main.validatorPackage.FormValidator;

import java.sql.Date;
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
        if (validUser(user)) userDAO.createUser(user);
    }

    private Boolean validUser(UserModel user) throws UserCreationException {
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();
        Date dateOfBirth = user.getDateOfBirth();
        char gender = user.getGender();
        String streetAndNumber = user.getStreetAndNumber();
        String phoneNumber = user.getPhoneNumber();
        String biography = user.getBio();
        Boolean isAdmin = user.isAdmin();
        Integer home = user.getHome();

        if (!FormValidator.validStringLength(email, 1, 50))
            throw new UserCreationException("L'email doit être compris entre 1 et 50 caractères");

        if (!FormValidator.validStringLength(username, 1, 20))
            throw new UserCreationException("Le nom d'utilisateur doit être compris entre 1 et 20 caractères");

        if (!FormValidator.validStringLength(password, 6, 20))
            throw new UserCreationException("Le mot de passe doit être compris entre 6 et 20 caractères");

        if (!FormValidator.validDateOfBirth(dateOfBirth.toLocalDate()))
            throw new UserCreationException("La date de naissance n'est pas valide");

        if (!FormValidator.validGender(gender))
            throw new UserCreationException("Le genre n'est pas valide");

        if (!FormValidator.validStringLength(streetAndNumber, 1, 255))
            throw new UserCreationException("La rue et le numéro ne sont pas valide");

        if (!FormValidator.isFieldNull(phoneNumber) && !FormValidator.validStringLength(phoneNumber, 1, 20))
            throw new UserCreationException("Le numéro de téléphone n'est pas valide");

        if (!FormValidator.isFieldNull(biography) && !FormValidator.validStringLength(biography, 1, 255))
            throw new UserCreationException("La biographie n'est pas valide");

        if (!FormValidator.validBoolean(isAdmin))
            throw new UserCreationException("Le statut d'administrateur n'est pas valide");

        if (!FormValidator.validId(home))
            throw new UserCreationException("Le code postal n'est pas valide");

        return true;
    }

    @Override
    public List<UserModel> getAllUsers() throws UserSearchException {
        return userDAO.getAllUsers();
    }

    @Override
    public UserModel getUser(int id) throws UserSearchException {return userDAO.getUser(id);}

    public Boolean updateUser(UserModel user) throws UpdateUserException {
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

    @Override
    public String getCountryNameByHome(int userId) throws CountriesDAOException {
        return userDAO.getCountryNameByHome(userId);
    }
}
