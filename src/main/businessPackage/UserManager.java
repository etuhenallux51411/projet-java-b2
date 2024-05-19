package main.businessPackage;

import main.dataAccessPackage.UserDAO;
import main.dataAccessPackage.UserDAOImpl;
import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.UserModel;

public class UserManager {
    private UserDAO userDAO;

    public UserManager() throws ConnectionDataAccessException {
        setUserDAO(new UserDAOImpl());
    }

    private void setUserDAO(UserDAO dao) throws ConnectionDataAccessException {
        this.userDAO = dao;
    }

    public Boolean createUser(UserModel user) throws ConnectionDataAccessException {
       return userDAO.createUser(user);
    }
}
