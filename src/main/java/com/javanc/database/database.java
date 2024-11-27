package com.javanc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
    public static Connection connectDB(){
        Connection connect = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bookstore";
            String username = "root";
            String password = "Nga31072004@";
            connect = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return connect;
    }

    public static void closeConection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println("e");
        }
    }
}
