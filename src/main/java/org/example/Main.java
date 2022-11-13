package org.example;

import org.example.models.ModelProduct;
import org.example.views.StartViews;

public class Main {
    public static void main(String[] args) {
        ModelProduct.startConnection();
        StartViews.startViews();
        ModelProduct.stopConnection();
    }
}