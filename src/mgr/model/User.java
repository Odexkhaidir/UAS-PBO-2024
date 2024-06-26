package mgr.model;

import mgr.pattern.Observer;
import mgr.pattern.ReportStrategy;

/**
 *
 * @author ACER
 */
public abstract class User implements Observer {
    private int id;
    private String name;
    private String password;
    private String role;
    private ReportStrategy reportStrategy;
    
    public User(String name, String password, String role, ReportStrategy reportStrategy) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.reportStrategy = reportStrategy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ReportStrategy getReportStrategy() {
        return reportStrategy;
    }

    public void setReportStrategy(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
    }
    
    @Override
    public void update(String message) {
        System.out.println("Notification for " + name + ": " + message);
    }
}
