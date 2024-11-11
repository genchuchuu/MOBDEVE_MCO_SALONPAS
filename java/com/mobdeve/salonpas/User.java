package com.mobdeve.salonpas;

public class User {
    public String firstName;
    public String lastName;
    public String email;
    public String birthdate;

    public User() {}

    public User(String firstName,String lastName, String email, String birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;
    }
}
