package com.mobdeve.salonpas;

public class Service {
    private String Name;
    private String Description;
    private String Duration;
    private String Price;
    private int Image;

    public Service(String Name, String Description, String Duration, String Price, int Image) {
        this.Name = Name;
        this.Description = Description;
        this.Duration = Duration;
        this.Price = Price;
        this.Image = Image;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getDuration() {
        return Duration;
    }

    public String getPrice() {
        return Price;
    }

    public int getImage() {
        return Image;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public void setPrice(String price) {
        Price = price;
    }
}

