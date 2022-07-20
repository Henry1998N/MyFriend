package com.example.myfrien1.ui.home;

public class Games {
    private String Description,Img,Price,name;

    @Override
    public String toString() {
        return "Games{" +
                "Description='" + Description + '\'' +
                ", Img='" + Img + '\'' +
                ", Price='" + Price + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Games(String description, String img, String price, String name) {
        Description = description;
        Img = img;
        Price = price;
        this.name = name;
    }

    public Games() {
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
