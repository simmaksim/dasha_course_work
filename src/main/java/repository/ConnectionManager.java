package repository;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ConnectionManager {
    INSTANCE;

    private Connection connection;

    private final static String USER = "root";
    private final static String PASSWORD = "password";

    ConnectionManager()
    {
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://database:3306/car_rental";
            connection = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection()
    {
        return connection;
    }
}
