package mgr.database;

import mgr.model.User;
import mgr.model.Student;
import mgr.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class UserDAO {
    public void saveUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user instanceof Student ? "student" : "admin");
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    if ("student".equals(role)) {
                        return new Student(rs.getString("name"), rs.getString("password"));
                    } else {
                        return new Admin(rs.getString("name"), rs.getString("password"));
                    }
                }
            }
        }
        return null;
    }

    public User getUserByName(String name) throws SQLException {
        String sql = "SELECT * FROM users WHERE name = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }
        }
        return null;
    }

    private User mapRowToUser(ResultSet rs) throws SQLException {
        String role = rs.getString("role");
        User user;
        if ("student".equals(role)) {
            user = new Student(rs.getString("name"), rs.getString("password"));
        } else {
            user = new Admin(rs.getString("name"), rs.getString("password"));
        }
        user.setId(rs.getInt("id"));
        return user;
    }
}
