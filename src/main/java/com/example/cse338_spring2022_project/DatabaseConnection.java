package com.example.cse338_spring2022_project;
import java.sql.*;
import java.sql.DriverManager;
public class DatabaseConnection {
    public Connection databaselink;
    public Connection getConnection() {
        String databaseName = "world";
        String username = "root";
        String password = "admin";
        String url = "jdbc:mysql://localhost/"+ databaseName;
        String driverName = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverName);
            databaselink = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return databaselink;
    }
    }
