package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ViewService extends AppCompatActivity {

    private TextView serviceNameTextView, serviceDescTextView, serviceDurationTextView, servicePriceTextView;
    private ImageView serviceImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service);

        serviceNameTextView = findViewById(R.id.serviceName);
        serviceDescTextView = findViewById(R.id.serviceDesc);
        serviceDurationTextView = findViewById(R.id.serviceDuration);
        servicePriceTextView = findViewById(R.id.servicePrice);
        serviceImg = findViewById(R.id.serviceImg);

        String srvName = getIntent().getStringExtra("name");
        String srvDesc = getIntent().getStringExtra("desc");
        String srvDuration = getIntent().getStringExtra("duration");
        String srvPrice = getIntent().getStringExtra("price");
        String srvImageUrl = getIntent().getStringExtra("imageUrl");

        serviceNameTextView.setText(srvName);
        serviceDescTextView.setText(srvDesc);
        serviceDurationTextView.setText(srvDuration);
        servicePriceTextView.setText(srvPrice);

        // Load image from URL using Glide
        if (srvImageUrl != null && !srvImageUrl.isEmpty()) {
            Glide.with(this)
                    .load(srvImageUrl)
                    .placeholder(R.drawable.placeholder) // Placeholder image
                    .into(serviceImg);
        } else {
            serviceImg.setImageResource(R.drawable.placeholder); // Default placeholder
        }
    }

    private List<String> getStylistsForService() {
        List<String> stylists = new ArrayList<>();
        stylists.add("Hirai Momo");
        stylists.add("Jeon Wonwoo");
        stylists.add("Miyawaki Sakura");
        stylists.add("Sabrina Carpenter");
        stylists.add("Olivia Rodrigo");
        return stylists;
    }
    public void backServicePage (View view) {
        startActivity(new Intent(ViewService.this, ServiceList.class));
    }
}
