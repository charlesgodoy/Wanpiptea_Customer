package com.burning_glow.wanpipteacustomer.model;

public class Products {

    private String Name;
    private int Medium;
    private int Large;

    public Products() {
    }

    public Products(String name, int medium, int large) {
        Name = name;
        Medium = medium;
        Large = large;
    }

    public String getName() {
        return Name;
    }

    public int getMedium() {
        return Medium;
    }

    public int getLarge() {
        return Large;
    }
}
