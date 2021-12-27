package com.contacts.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnection {
    private static final Logger log = LogManager.getLogger(DatabaseConnection.class);
    private static final Properties props = new Properties();
    private static Connection connection = null;
    private static final String defaultSource = "D:\\intership\\untitled\\src\\main\\resources\\db.properties";


    static {
        log.info("DataBaseConnection creation from default source");
        try (FileInputStream is = new FileInputStream(defaultSource)) {
            props.load(is);
        } catch (IOException ex) {
            log.error("Default properties file is missing" + ex);
            throw new RuntimeException("Properties file is missing", ex);
        }
    }


    public static Connection getConnection() {
        log.info("Try to get db connection");
        if (connection == null) {
            log.trace("Create new connection");
            try {
                Class.forName(props.getProperty("db.driver"));
                connection = DriverManager.getConnection(
                        props.getProperty("db.url"),
                        props.getProperty("db.user"),
                        props.getProperty("db.password"));
            } catch (ClassNotFoundException ex) {
                log.error("Driver missing");
                throw new DatabaseException("Driver missing",ex);
            } catch (SQLException ex) {
                log.error("No connection to database");
                throw new DatabaseException("No connection",ex);
            }
        }
        log.info("Return connection" + connection);
        return connection;
    }

}
