package com.mobdeve.salonpas;

public class Appointment {
    private String date;
    private String services;
    private String stylist;
    private int id;

    public Appointment(String date, String services, String stylist) {
        this.date = date;
        this.services = services;
        this.stylist = stylist;
    }

    public String getDate() {
        return date;
    }

    public String getServices() {
        return services;
    }

    public String getStylist() {
        return stylist;
    }

    public int getId() {return id;}
}
