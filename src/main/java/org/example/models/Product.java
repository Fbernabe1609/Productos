package org.example.models;

public class Product {
    private int productID;
    private String name;
    private float price;
    private int categoryID;
    private String category;

    public Product(int productID, String name, float price, int categoryID, String category) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
        this.category = category;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "ID-Producto=" + productID +
                ", nombre='" + name + '\'' +
                ", precio=" + price +
                "€, ID-Categoría=" + categoryID +
                ", categoría='" + category + '\'' +
                '}';
    }
}
