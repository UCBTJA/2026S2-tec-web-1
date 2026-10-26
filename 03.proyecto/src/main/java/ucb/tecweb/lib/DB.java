package ucb.tecweb.lib;

import java.sql.Connection;

public class DB {
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
            return null;
        }
        try {
            return java.sql.DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/ucb", "ucb",
                    "Tarija2026");
        } catch (java.sql.SQLException e) {
            System.out.println("Error connecting to PostgreSQL database.");
            e.printStackTrace();
            return null;
        }
    }
}
