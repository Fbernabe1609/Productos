package org.example.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

        String sqlSentences = "update productos set nombre= ?, precio = ? where nombre = ?";
        PreparedStatement sentencia = null;
        try {
            sentencia = connection1.getConnection().prepareStatement(sqlSentences);
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

        String sqlSentences = "insert into productos (Nombre, Precio, Cantidad) values (?, ?, ?)";
        PreparedStatement sentencia = null;
        try {
            sentencia = connection1.getConnection().prepareStatement(sqlSentences);
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
        String sqlSentences = "delete from productos where nombre = ?";
        PreparedStatement sentencia = null;
        try {
            sentencia = connection1.getConnection().prepareStatement(sqlSentences);
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
        String sqlSentences = "select * from productos where nombre = ?";
        PreparedStatement sentencia = null;
        ResultSet resultSet;
        try {
            sentencia = connection1.getConnection().prepareStatement(sqlSentences);
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

    public static ResultSet executeSelect() throws SQLException {
        String query = "select *,(select Categoria from categorias where CategoriaID = productos.CategoriaID) as Categoria from productos";
        Statement stmt = connector.getConnection().createStatement();
        return stmt.executeQuery(query);
    }
}
