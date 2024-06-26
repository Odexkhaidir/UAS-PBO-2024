package mgr.controller;

import mgr.database.UserDAO;
import mgr.model.User;

import java.sql.SQLException;

public class LoginController {
    private UserDAO userDAO;

    public LoginController() {
        this.userDAO = new UserDAO();
    }

    public User authenticateUser(String name) {
        try {
            return userDAO.getUserByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
