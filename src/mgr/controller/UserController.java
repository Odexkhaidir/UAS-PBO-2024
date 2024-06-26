package mgr.controller;

import mgr.database.UserDAO;
import mgr.model.Admin;
import mgr.model.Student;
import mgr.model.User;

import java.sql.SQLException;
import mgr.pattern.ReportNotifier;

/**
 *
 * @author ACER
 */
public class UserController {
    private UserDAO userDAO = new UserDAO();
    
    public User createUser(String role, String name, String password) {
        User user;
        if("student".equalsIgnoreCase(role)) {
            user = new Student(name, password);
        } else if("admin".equalsIgnoreCase(role)) {
            user = new Admin(name, password);
        } else {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
        
        try {
            userDAO.saveUser(user);
            ReportNotifier.getInstance().addObserver(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
