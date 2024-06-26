package mgr.controller;

import mgr.database.ReportDAO;
import mgr.model.Report;
import mgr.model.Report.ReportState;
import mgr.model.User;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
/**
 *
 * @author ACER
 */

public class ReportController {
    private ReportDAO reportDAO = new ReportDAO();

    public Report createReport(User user, String title, String description, int status, ReportState reportState) {
        // Check for existing similar report
        List<Report> existingReports = getReportsByUser(user);
        for (Report report : existingReports) {
            if (report.getTitle().equalsIgnoreCase(title) && report.getDescription().equalsIgnoreCase(description)) {
                return report;
            }
        }

        // No similar report found, create new report
        Report report = new Report(user, title, description, status, reportState);
        try {
            reportDAO.saveReport(report);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }

    public void updateReport(Report report, String title, String description) {
        report.setTitle(title);
        report.setDescription(description);
        report.setUpdated_at(LocalDateTime.now());
        try {
            reportDAO.updateReport(report);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Report> getReports() {
        try {
            return reportDAO.getReports();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Report> getReportsByUser(User user) {
        // Implement this method to fetch reports by a specific user
        try {
            return reportDAO.getReportsById(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
