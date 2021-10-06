package edu.grow;

import java.io.Serializable;

public class Shop {
    String name;
    int price;
    int thumbnail;

    public Shop(String name, int price, int thumbnail) {
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
    }
}
