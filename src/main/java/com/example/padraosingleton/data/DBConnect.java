package com.example.padraosingleton.data;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class DBConnect {

    private String driver;
    private String url;
    private String database;
    private String user;
    private String password;
    @Getter
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    private static DBConnect instance;


    private DBConnect() {
        loadConfig();
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBConnect getInstance() {
        if (instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }

    private void loadConfig() {
        Properties fileConfig = new Properties();
        try (FileInputStream fis = new FileInputStream("application.properties")) {
            fileConfig.load(fis);
            driver = fileConfig.getProperty("db.driver");
            url = fileConfig.getProperty("db.url");
            user = fileConfig.getProperty("db.user");
            password = fileConfig.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet select(String query) throws Exception {
        statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public int executeDMLStatement(String query) throws Exception {
        statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    public void releaseResources() {
        try {
            if (statement != null) statement.close();
            if (preparedStatement != null) preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
