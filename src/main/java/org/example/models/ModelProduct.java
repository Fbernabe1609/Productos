package org.example.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelProduct {

    private static String dbUser = "root";
    private static String dbPassword = "";
    private static String db = "productosisaac";
    private static Connector connector = new Connector(dbUser, dbPassword, db);

    public static void startConnection() {
        try {
            connector.connect();
        } catch (SQLException e) {
            System.out.println("Ha ocurrido el siguiente error: " + e);
        }
    }

    public static void stopConnection() {
        try {
            connector.disconnect();
        } catch (SQLException e) {
            System.out.println("Ha ocurrido el siguiente error: " + e);
        }
    }

    public void update(Connector connection1) {

        String sentenciasql = "update productos set nombre= ?, precio = ? where nombre = ?";
        PreparedStatement sentencia = null;
        try {
            sentencia = connection1.getConnection().prepareStatement(sentenciasql);
            sentencia.setString(1,"Tomate");
            sentencia.setFloat(2,5);
            sentencia.setString(3,"Arroz");
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void insert(Connector connection1) {

        String sentenciasql = "insert into productos (Nombre, Precio, Cantidad) values (?, ?, ?)";
        PreparedStatement sentencia = null;
        try {
            sentencia = connection1.getConnection().prepareStatement(sentenciasql);
            sentencia.setString(1,"Tomate");
            sentencia.setFloat(2,5);
            sentencia.setInt(3,6);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void delete(Connector connection1){
        String sentenciasql = "delete from productos where nombre = ?";
        PreparedStatement sentencia = null;
        try {
            sentencia = connection1.getConnection().prepareStatement(sentenciasql);
            sentencia.setString(1,"Tomate");
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void select(Connector connection1){
        String sentenciasql = "select * from productos where nombre = ?";
        PreparedStatement sentencia = null;
        ResultSet resultSet = null;
        try {
            sentencia = connection1.getConnection().prepareStatement(sentenciasql);
            sentencia.setString(1,"Tomate");
            resultSet = sentencia.executeQuery();
            while (resultSet.next()){
                System.out.println("Nombre: " + resultSet.getString(2) + "\nPrecio: " + resultSet.getFloat(3) + "\nCantidad: " + resultSet.getInt(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void selectAll(Connector connection1){
        String sentenceSQLProducts = "select * from productos";
        String sentenceSQLCategories = "select * from productos";
    }
}
