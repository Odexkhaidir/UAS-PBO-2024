package mgr.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import mgr.model.Admin;
import mgr.model.Report;
import mgr.model.Student;
import mgr.model.User;

/**
 *
 * @author ACER
 */

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:sqlite:./Database/mgr_db.db";
    private static DatabaseUtil instance;

    public static DatabaseUtil getInstance() {
        if (instance == null) {
            instance = new DatabaseUtil();
        }
        return instance;
    }

    public static void createTables() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement()) {
            String sqlUser = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "role TEXT NOT NULL)";
            stmt.execute(sqlUser);

            String sqlReport = "CREATE TABLE IF NOT EXISTS reports (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_id INTEGER," +
                    "title TEXT NOT NULL," +
                    "description TEXT," +
                    "created_at TEXT," +
                    "updated_at TEXT," +
                    "status INTEGER," +
                    "report_state TEXT," +
                    "FOREIGN KEY(user_id) REFERENCES users(id))";
            stmt.execute(sqlReport);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private User getUserById(int userId) throws SQLException {
        // Implement method to fetch user by ID
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                String name = rs.getString("name");
                String password = rs.getString("password");
                if (role.equals("student")) {
                    Student student = new Student(name, password);
                    student.setId(userId);
                    return student;
                } else if (role.equals("admin")) {
                    Admin admin = new Admin(name, password);
                    admin.setId(userId);
                    return admin;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Report> getListReports() throws SQLException {
        String sql = "SELECT * FROM reports";
        List<Report> listReports = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Constructing Report object from ResultSet
                Report report = new Report(
                        getUserById(rs.getInt("user_id")),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("status"),
                        Report.ReportState.valueOf(rs.getString("report_state")));
                report.setId(rs.getInt("id"));
                report.setCreated_at(LocalDateTime.parse(rs.getString("created_at")));
                report.setUpdated_at(LocalDateTime.parse(rs.getString("updated_at")));
                report.setStatus(rs.getInt("status"));
                listReports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listReports;
    }
}
