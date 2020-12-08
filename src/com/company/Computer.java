package com.company;

public class Computer {

    private String name = "";
    private String firm = "";
    private String tech = "";
    private double price = 0;
    private int warranty = 0;

    public Computer(String name, String firm, String tech, double price, int warranty){
        this.name = name;
        this.firm = firm;
        this.tech = tech;
        this.price = price;
        this.warranty = warranty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public String getFirm() {
        return firm;
    }

    public String getTech() {
        return tech;
    }

    public double getPrice() {
        return price;
    }

    public int getWarranty() {
        return warranty;
    }
}

