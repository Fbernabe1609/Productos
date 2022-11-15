package org.example.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public static void update(float price, String name) {

        String sqlSentences = "update productos set precio = ? where nombre = ?";
        PreparedStatement sentence = null;
        try {
            sentence = connector.getConnection().prepareStatement(sqlSentences);
            sentence.setFloat(1,price);
            sentence.setString(2,name);
            sentence.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (sentence != null) {
                try {
                    sentence.close();
                } catch (SQLException e) {
                    System.out.println("Ha ocurrido el siguiente error: " + e);
                }
            }
        }
    }

    public static void insert(String name, float price, String category) {

        String sqlSentences = "insert into productos (Nombre, Precio, CategoriaID) values (?, ?, (select CategoriaID from categorias where Categoria = ?))";
        PreparedStatement sentence = null;
        try {
            sentence = connector.getConnection().prepareStatement(sqlSentences);
            sentence.setString(1,name);
            sentence.setFloat(2,price);
            sentence.setString(3,category);
            sentence.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ha ocurrido el siguiente error: " + e);
        } finally {
            if (sentence != null) {
                try {
                    sentence.close();
                } catch (SQLException e) {
                    System.out.println("Ha ocurrido el siguiente error: " + e);
                }
            }
        }
    }

    public static void delete(String name){
        String sqlSentences = "delete from productos where nombre = ?";
        PreparedStatement sentence = null;
        try {
            sentence = connector.getConnection().prepareStatement(sqlSentences);
            sentence.setString(1,name);
            sentence.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ha ocurrido el siguiente error: " + e);
        } finally {
            if (sentence != null) {
                try {
                    sentence.close();
                } catch (SQLException e) {
                    System.out.println("Ha ocurrido el siguiente error: " + e);
                }
            }
        }
    }

    public static boolean selectProduct(String data){
        String sqlSentences = "select * from productos where Nombre = ?";
        return selection(data, sqlSentences);
    }

    public static boolean selectCategory(String data){
        String sqlSentences = "select * from categorias where Categoria = ?";
        return selection(data, sqlSentences);
    }

    public static boolean selection(String data, String sqlSentences) {
        PreparedStatement sentence = null;
        ResultSet resultSet;
        boolean select = false;

        try {
            sentence = connector.getConnection().prepareStatement(sqlSentences);
            sentence.setString(1,data);
            resultSet = sentence.executeQuery();
            if (!resultSet.next()) {
                select = true;
            }
            sentence.close();
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("Ha ocurrido el siguiente error: " + e);
        }
        return select;
    }

    public static ResultSet executeSelect() throws SQLException {
        String query = "select *,(select Categoria from categorias where CategoriaID = productos.CategoriaID) as Categoria from productos";
        Statement stmt = connector.getConnection().createStatement();
        return stmt.executeQuery(query);
    }

    public static ArrayList<Product> filterSelect(float data1, float data2, ArrayList<Product> products) {
        String sqlSentences = "select *, (select Categoria from categorias where CategoriaID = productos.CategoriaID) from productos where precio between ? and ?";
        ResultSet resultSet = null;
        PreparedStatement sentence = null;
        try {
            sentence = connector.getConnection().prepareStatement(sqlSentences);
            sentence.setFloat(1,data1);
            sentence.setFloat(2,data2);
            resultSet = sentence.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt(1),resultSet.getString(2),resultSet.getFloat(3),resultSet.getInt(4),resultSet.getString(5)));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Ha ocurrido el siguiente error: " + e);
        } finally {
            if (sentence != null) {
                try {
                    sentence.close();
                } catch (SQLException e) {
                    System.out.println("Ha ocurrido el siguiente error: " + e);
                }
            }
        }
        return products;
    }
}