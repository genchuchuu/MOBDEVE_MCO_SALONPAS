package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class StylistProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylist_profile);

        // Retrieve stylist details passed from the previous activity
        String name = getIntent().getStringExtra("stylist_name");
        String photo = getIntent().getStringExtra("stylist_photo");
        int years = getIntent().getIntExtra("stylist_experience", 0);
        double rating = getIntent().getDoubleExtra("stylist_rating", 0.0);
        String services = getIntent().getStringExtra("stylist_services");

        // Bind views
        TextView nameTextView = findViewById(R.id.stylistName);
        ImageView photoImageView = findViewById(R.id.stylistImage);
        TextView ratingTextView = findViewById(R.id.stylistRating);
        TextView experienceTextView = findViewById(R.id.stylistExperience);
        TextView servicesTextView = findViewById(R.id.stylistServices);

        // Set stylist details to views
        nameTextView.setText(name);
        ratingTextView.setText(String.format("%.1f", rating));
        experienceTextView.setText(String.format("%d years", years));
        servicesTextView.setText(services);

        // Load image dynamically with Glide
        if (photo != null && !photo.isEmpty()) {
            Glide.with(this)
                    .load(photo)
                    .placeholder(R.drawable.placeholder) // Placeholder image
                    .into(photoImageView);
        } else {
            photoImageView.setImageResource(R.drawable.placeholder); // Default image
        }
    }

    // Navigation Methods
    public void openUserMainPage(View view) {
        Intent intent = new Intent(StylistProfile.this, UserMainPageActivity.class);
        startActivity(intent);
    }

    public void openServicesList(View view) {
        Intent intent = new Intent(StylistProfile.this, ServiceList.class);
        startActivity(intent);
    }

    public void openStylistList(View view) {
        Intent intent = new Intent(StylistProfile.this, ViewStylistList.class);
        startActivity(intent);
    }

    public void openReservationPage(View view) {
        Intent intent = new Intent(StylistProfile.this, AppointmentReservationActivity.class);
        startActivity(intent);
    }

    public void openNotificationPage(View view) {
        Intent intent = new Intent(StylistProfile.this, AppointmentNotificationActivity.class);
        startActivity(intent);
    }
}
