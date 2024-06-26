package mgr.controller;

import mgr.pattern.ReportNotifier;
import mgr.pattern.ReportStrategy;
import mgr.model.Report;
import mgr.model.User;

/**
 *
 * @author ACER
 */
public class StudentController implements ReportStrategy {
    private ReportController reportController = new ReportController();

    @Override
    public void createReport(User user, String title, String description) {
        Report report = reportController.createReport(user, title, description, 0, Report.ReportState.OPEN);
        ReportNotifier.getInstance().reportCreated(report.getTitle() + " - " + report.getDescription());
    }

    @Override
    public void updateReport(Report report, String title, String description) {
        reportController.updateReport(report, title, description);
        ReportNotifier.getInstance().reportUpdated(report.getTitle() + " - " + report.getDescription());
    }
}
