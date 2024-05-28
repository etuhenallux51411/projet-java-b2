package test;

import main.controllerPackage.UserController;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.UserCreationException;
import main.exceptionPackage.UserDeletionException;
import main.exceptionPackage.UserSearchException;
import main.modelPackage.UserModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private UserController userController;
    private UserModel user;

    @BeforeEach
    void setUp() throws ConnectionDataAccessException {
        userController = new UserController();
    }

    @Test
    void createUser() throws UserCreationException {
        user = new UserModel();
        user.setEmail("dawd@dwa.coddawvv");
        user.setUsername("vradwavadwdwadwadw");
        user.setPassword("dwdwad");
        user.setDateOfBirth(Date.valueOf("1990-01-01"));
        user.setGender('m');
        user.setStreetAndNumber("test street");
        user.setAdmin(false);
        user.setHome(1);
        assertTrue(userController.createUser(user));
    }
}