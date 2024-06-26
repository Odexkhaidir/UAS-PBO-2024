package mgr.pattern;

/**
 *
 * @author ACER
 */
public class ReportNotifier extends Announcement {
    private static ReportNotifier instance = new ReportNotifier();

    private ReportNotifier() {}

    public static ReportNotifier getInstance() {
        return instance;
    }

    public void reportCreated(String reportDetails) {
        notifyObservers("New report created: " + reportDetails);
    }

    public void reportUpdated(String reportDetails) {
        notifyObservers("Report updated: " + reportDetails);
    }
}
