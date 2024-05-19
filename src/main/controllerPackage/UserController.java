package main.controllerPackage;

import main.businessPackage.UserManager;
import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.UserModel;

public class UserController {
    private UserManager userManager;

    public UserController() throws ConnectionDataAccessException {
        userManager = new UserManager();
    }

    public Boolean createUser(UserModel user) throws ConnectionDataAccessException {
        return userManager.createUser(user);
    }
}
