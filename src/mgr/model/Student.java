package mgr.model;

import mgr.pattern.StudentReportStrategy;

/**
 *
 * @author ACER
 */
public class Student extends User {
    public Student(String username, String password) {
        super(username, password, "student", new StudentReportStrategy());
    }
}
