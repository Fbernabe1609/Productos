package org.example.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private String db, user, pass, host;
    private Connection connection;

    public Connector(String user, String pass, String db) {
        this.host = "localhost";
        this.user = user;
        this.pass = pass;
        this.db = db;
        this.connection = null;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private void registerDriver() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Ha ocurrido el siguiente error al conectar con MySQL: " + e);
        }
    }

    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            registerDriver();
            String url = "jdbc:mysql://" + host +"/"+db+"";
            connection = DriverManager.getConnection(url,user,pass);
        }
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
