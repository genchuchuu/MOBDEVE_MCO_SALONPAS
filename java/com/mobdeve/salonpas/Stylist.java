package com.mobdeve.salonpas;

public class Stylist {
    private String name;
    private String photo;
    private int yearsOfExperience;
    private double rating;
    private String services;

    public Stylist(String name, String photo, int years, double rating, String services) {
        this.name = name;
        this.photo = photo;
        this.yearsOfExperience = years;
        this.rating = rating;
        this.services = services;
    }

    public String getName() { return name; }
    public String getPhoto() { return photo; }
    public int getYearsOfExperience() { return yearsOfExperience; }
    public double getRating() { return rating; }
    public String getServices() { return services; }

}