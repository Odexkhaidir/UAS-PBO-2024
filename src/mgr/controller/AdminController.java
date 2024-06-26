package mgr.controller;

import mgr.pattern.ReportNotifier;
import mgr.pattern.ReportStrategy;
import mgr.model.Message;
import mgr.model.Report;
import mgr.model.Report.ReportState;
import mgr.model.User;

/**
 *
 * @author ACER
 */
public class AdminController implements ReportStrategy {
    Message message = new Message("");
    
    public void confirmReport(Report report) {
        if(report.getReportState() == ReportState.OPEN) {
            //Seandainya user (owner) ingin memberitahu barang yang hilang pada admin
            if(report.getStatus() == 0) {
                message.setContent("Laporan diterima! Kami akan segera mengumumkan barang Anda");
                
            } else if(report.getStatus() == 1) {
                report.setReportState(ReportState.CONFIRMED);
                message.setContent("Laporan telah dikonfirmasi oleh Admin.");
            }
        } else {
           message.setContent("Laporan tidak dikonfirmasi oleh Admin!"); 
        }
    }
    
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
}
