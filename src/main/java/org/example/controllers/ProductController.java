package org.example.controllers;

import org.example.models.ModelProduct;
import org.example.models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController {

    private static ArrayList <Product> products = new ArrayList<>();

    public static ArrayList<Product> createProducts() {
        products.clear();
        try {
            ResultSet resultSet = ModelProduct.executeSelect();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt(1),resultSet.getString(2),resultSet.getFloat(3),resultSet.getInt(4),resultSet.getString(5)));
            }
        } catch (SQLException e) {
            System.out.println("Ha ocurrido el siguiente error: " + e);
        }
        return products;
    }

    public static void insertProducts(String name, float price, String category) {
        ModelProduct.insert(name, price, category);
    }

    public static boolean checkName(String name) {
        return ModelProduct.selectProduct(name);
    }

    public static boolean checkCategory(String category) {
        return ModelProduct.selectCategory(category);
    }
}
