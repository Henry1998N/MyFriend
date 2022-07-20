package com.example.myfrien1.ui.home;

public class DataModal {

    private String name;
    private String imgUrl;
    private String Description;
    private String Price;

    public DataModal() {
    }

    public DataModal(String name, String imgUrl, String description, String price) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.Description = description;
        this.Price = price;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public String getPrice() {
        return Price;
    }

    public String getDescription() {
        return Description;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DataModal{" +
                "name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", Description='" + Description + '\'' +
                ", Price='" + Price + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
