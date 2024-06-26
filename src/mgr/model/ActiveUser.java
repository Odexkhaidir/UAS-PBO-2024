package mgr.model;

//import mgr.model.User;
/**
 *
 * @author ACER
 */
public class ActiveUser {
    public static User activeUser;
    public static Report userReport;

    /**
     *
     * @param user
     */
    public static void setUser(User user) {
        activeUser = user;
    }
    
    public static User getUser() {
        return activeUser;
    }
    
    public static void setReport(Report report) {
        userReport = report;
    }
    
    public static Report getReport() {
        return userReport;
    }
    
}
