package com.example.myfrien1.ui.slideshow;

public class CartPro {
    private String Description,Img,Price,name;

    public CartPro(String description, String img, String price, String name) {

        Description = description;
        Img = img;
        Price = price;
        this.name = name;

    }

    public CartPro() {
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
