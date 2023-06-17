package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    public static final String DRIVER = "jdbc_driver";
    public static final String URL = "db_url";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    @Override
    public Connection createConnection() {
        Connection connection = null;
        Properties properties = loadProperties();
        String url = properties.getProperty(URL);
        String user = properties.getProperty(USER);
        String password = properties.getProperty(PASSWORD);
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Properties loadProperties() {
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("h2database.properties");
        try {
            props.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}

