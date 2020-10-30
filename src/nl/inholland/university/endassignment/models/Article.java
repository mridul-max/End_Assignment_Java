package nl.inholland.university.endassignment.models;

import nl.inholland.university.endassignment.data.UserDao;

public class Article {
    private String brand;
    private String model;
    private boolean acoustic;
    private Type type;
    private double price;


    private int iD;
    private int quantity;
    private static int autoId =0;
    public Article(String brand, String model, boolean acoustic, Type type, double price,int quantity) {
        this.brand = brand;
        this.model = model;
        this.acoustic = acoustic;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.iD = ++Article.autoId;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAcoustic() {
        return acoustic;
    }
    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setAcoustic(boolean acoustic) {
        this.acoustic = acoustic;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
