package com.mobdeve.salonpas;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewService extends AppCompatActivity {

    private TextView serviceNameTextView;
    private TextView serviceDescTextView;
    private TextView serviceDurationTextView;
    private TextView servicePriceTextView;
    private ImageView serviceImg;
    private RecyclerView stylistRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service);

        String srvName = getIntent().getStringExtra("name");
        String srvDesc = getIntent().getStringExtra("desc");
        String srvDuration = getIntent().getStringExtra("duration");
        String srvPrice = getIntent().getStringExtra("price");
        int srvImage = getIntent().getIntExtra("image", 0);


        serviceNameTextView = findViewById(R.id.serviceName);
        serviceDescTextView = findViewById(R.id.serviceDesc);
        serviceDurationTextView = findViewById(R.id.serviceDuration);
        servicePriceTextView = findViewById(R.id.servicePrice);
        serviceImg = findViewById(R.id.serviceImg);

        serviceNameTextView.setText(srvName);
        serviceDescTextView.setText(srvDesc);
        serviceDurationTextView.setText(srvDuration);
        servicePriceTextView.setText(srvPrice);
        serviceImg.setImageResource(srvImage);

        stylistRecyclerView = findViewById(R.id.StylistRecView);
        stylistRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> stylistList = getStylistsForService();
        StylistAdapter stylistAdapter = new StylistAdapter(stylistList);
        stylistRecyclerView.setAdapter(stylistAdapter);
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


}
