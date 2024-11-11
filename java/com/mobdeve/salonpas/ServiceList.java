package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ServiceList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ServiceAdapter adapter;
    private List<Service> serviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        recyclerView = findViewById(R.id.ServiceListRecView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        loadServiceList();

        adapter = new ServiceAdapter(serviceList, service -> {
            Intent intent = new Intent(this, ViewService.class);
            intent.putExtra("name", service.getName());
            intent.putExtra("desc", service.getDescription());
            intent.putExtra("duration", service.getDuration());
            intent.putExtra("price", service.getPrice());
            intent.putExtra("image", service.getImage());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }

    private void loadServiceList() {
        serviceList = new ArrayList<>();
        serviceList.add(new Service("Hair Cut", "Basic haircut", "30 mins", "$15", R.drawable.haircut));
        serviceList.add(new Service("Hair Color", "Hair coloring", "2 hours", "$60", R.drawable.haircolor));
        serviceList.add(new Service("Hair Style", "Hair styling", "45 mins", "$25", R.drawable.hairstyle));
        serviceList.add(new Service("Hair Treatment", "Hair treatment", "1 hour", "$50", R.drawable.hairtreatment));
    }

    public void openUserMainPage(View view) {
        Intent intent = new Intent(ServiceList.this, UserMainPageActivity.class);
        startActivity(intent);
    }

    public void openServicesList(View view) {
        Intent intent = new Intent(ServiceList.this, ServiceList.class);
        startActivity(intent);
    }

    public void openStylistList(View view) {
        Intent intent = new Intent(ServiceList.this, ViewStylistList.class);
        startActivity(intent);
    }

    public void openReservationPage (View view) {
        Intent intent = new Intent(ServiceList.this, AppointmentReservationActivity.class);
        startActivity(intent);
    }

    public void openNotificationPage (View view) {
        Intent intent = new Intent(ServiceList.this, AppointmentNotificationActivity.class);
        startActivity(intent);
    }

    public void openProfilePage (View view) {
        Intent intent = new Intent(ServiceList.this, ProfileActivity.class);
        startActivity(intent);
    }

}

