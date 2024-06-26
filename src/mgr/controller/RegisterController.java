package mgr.controller;

import mgr.database.UserDAO;
import mgr.model.User;
import mgr.model.Student;
import mgr.model.Admin;

import java.sql.SQLException;

public class RegisterController {
    private UserDAO userDAO;

    public RegisterController() {
        this.userDAO = new UserDAO();
    }

    public boolean registerUser(String name, String password, String role) {
        User user;
        if ("student".equalsIgnoreCase(role)) {
            user = new Student(name,password);
        } else if ("admin".equalsIgnoreCase(role)) {
            user = new Admin(name,password);
        } else {
            return false;
        }

        try {
            userDAO.saveUser(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
