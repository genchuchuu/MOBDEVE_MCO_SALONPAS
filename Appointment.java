package com.mobdeve.salonpas;

public class Appointment {
    private String date;
    private String serviceName;
    private String stylistName;
    private String time;

    public Appointment(String date, String serviceName, String stylistName, String time) {
        this.date = date;
        this.serviceName = serviceName;
        this.stylistName = stylistName;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getStylistName() {
        return stylistName;
    }

    public String getTime() {
        return time;
    }
}