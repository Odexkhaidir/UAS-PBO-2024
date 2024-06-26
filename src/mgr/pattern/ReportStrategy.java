package mgr.pattern;

import mgr.model.Report;
import mgr.model.User;

/**
 *
 * @author ACER
 */
public interface ReportStrategy {
    void createReport(User user, String title, String description);
    void updateReport(Report report, String title, String description);
}
