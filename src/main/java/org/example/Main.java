package org.example;

import org.example.models.Connector;
import org.example.views.StartViews;

import java.sql.SQLException;

public class Main {
    static Connector connector = new Connector("root", "", "restaurant");
    public static void main(String[] args) {
        try {
            connector.connect();
            StartViews.startViews();
            connector.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}