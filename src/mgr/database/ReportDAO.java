package mgr.database;

import mgr.model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {
    public void saveReport(Report report) throws SQLException {
        String sql = "INSERT INTO reports (user_id, username, title, description, created_at, updated_at, status, report_state) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, report.getUser().getId());
            pstmt.setString(2, report.getUser().getName());
            pstmt.setString(3, report.getTitle());
            pstmt.setString(4, report.getDescription());
            pstmt.setString(5, report.getCreated_at().toString());
            pstmt.setString(6, report.getUpdated_at().toString());
            pstmt.setInt(7, report.getStatus());
            pstmt.setString(8, report.getReportState().toString());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    report.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void updateReport(Report report) throws SQLException {
        String sql = "UPDATE reports SET title = ?, description = ?, updated_at = ?, status = ?, report_state = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, report.getTitle());
            pstmt.setString(2, report.getDescription());
            pstmt.setString(3, report.getUpdated_at().toString());
            pstmt.setInt(4, report.getStatus());
            pstmt.setString(5, report.getReportState().toString());
            pstmt.setInt(6, report.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Report> getReports() throws SQLException {
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

    public List<Report> getReportsById(int reportId) throws SQLException {
        String sql = "SELECT * FROM reports WHERE user_id = ?";
        List<Report> reports = new ArrayList<>();
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
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public Report searchReport(int reportId, String title, String description) {
        String sql = "SELECT * FROM reports WHERE id = ? AND title = ? AND description = ?";
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reportId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
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
                return report;
            }
        } catch (SQLException e) {
        }
        return null;
    }
}
