package mgr.model;

import mgr.pattern.AdminReportStrategy;

/**
 *
 * @author ACER
 */
public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password, "admin", new AdminReportStrategy());
    }
}
