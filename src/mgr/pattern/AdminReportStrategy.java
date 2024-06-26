package mgr.pattern;

import mgr.controller.ReportController;
import mgr.model.Report;
import mgr.model.User;

/**
 *
 * @author ACER
 */
public class AdminReportStrategy implements ReportStrategy {
    private ReportController reportController = new ReportController();

    @Override
    public void createReport(User user, String title, String description) {
        Report report = reportController.createReport(user, title, description, 0, Report.ReportState.CONFIRMED);
        ReportNotifier.getInstance().reportCreated(report.getTitle() + " - " + report.getDescription());
    }

    @Override
    public void updateReport(Report report, String title, String description) {
        reportController.updateReport(report, title, description);
        ReportNotifier.getInstance().reportUpdated(report.getTitle() + " - " + report.getDescription());
    }
    
    public void deleteReport() {
        
    }
}
