package com.miwth.allure_spa.model;

public class CartItem {
    private String name;
    private String price;
    private String qty;
    private String image;

    public CartItem(String name, String price, String qty, String image) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.image = image;
    }

    public CartItem() {
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQty() {
        return qty;
    }

    public String getImage() {
        return image;
    }
}
