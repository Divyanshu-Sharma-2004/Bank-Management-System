package bank.management.system;

import java.sql.*;

public class Conn {

    public Connection c;

    public Conn() {
        try {
            // Load JDBC driver (optional in newer Java versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the MySQL database
            c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bankmanagementsystem", // database URL
                "root",                                             // database username
                "root"                                              // database password
            );

        } catch (Exception e) {
            // Print error message if connection fails
            System.out.println("Database Connection Error: " + e);
        }
    }
}
