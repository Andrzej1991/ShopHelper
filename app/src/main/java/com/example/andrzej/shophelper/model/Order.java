package com.example.andrzej.shophelper.model;

/**
 * Created by Andrzej on 2017-09-17.
 */

public class Order {

    private boolean sent;
    private int quantity;
    private String name;
    private String address;
    private String description;
    private String numberOfLanding;


    public boolean getSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumberOfLanding() {
        return numberOfLanding;
    }

    public void setNumberOfLanding(String numberOfLanding) {
        this.numberOfLanding = numberOfLanding;
    }
}
