import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(100), email VARCHAR(100))";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public Employee insertEmployee(Employee data) throws SQLException {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?) RETURNING id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, data.getName());
            pstmt.setString(2, data.getEmail());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getInt("id"), data.getName(), data.getEmail());
            }
            return new Employee(data.getName(), data.getEmail());
        }
    }

    public Employee updateEmployee(int id, Employee data) throws SQLException {
        Employee employee = null;
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, data.getName());
            pstmt.setString(2, data.getEmail());
            pstmt.setInt(3, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                employee = new Employee(id, data.getName(), data.getEmail());
            }
            return employee;
        }
    }

    public Employee deleteEmployee(int id) throws SQLException {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            String sql = "DELETE FROM users WHERE id = ?";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
        }
        return employee;
    }

    public Employee getEmployeeById(int id) throws SQLException {
        Employee employee = null;
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
                }
            }
        }
        return employee;
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
            }
        }
        return users;
    }
}
