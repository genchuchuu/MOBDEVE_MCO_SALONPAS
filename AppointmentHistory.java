package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AppointmentHistory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppointmentAdapter adapter;
    private List<Appointment> pastAppointments = new ArrayList<>();
    private List<String> appointmentIds = new ArrayList<>();
    private DatabaseReference databaseReference;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_history);

        recyclerView = findViewById(R.id.appointmentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AppointmentAdapter(pastAppointments, appointmentIds);
        recyclerView.setAdapter(adapter);

        fetchAppointments();
    }

    private void fetchAppointments() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference.child("Reservations").orderByChild("userId").equalTo(currentUserId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        pastAppointments.clear();
                        appointmentIds.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String appointmentId = snapshot.getKey();
                            String date = snapshot.child("date").getValue(String.class);
                            String time = snapshot.child("time").getValue(String.class);
                            String serviceId = snapshot.child("serviceId").getValue(String.class);
                            String stylistId = snapshot.child("stylistId").getValue(String.class);

                            fetchServiceAndStylistDetails(appointmentId, date, time, serviceId, stylistId);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("AppointmentHistory", "Error fetching reservations", error.toException());
                    }
                });
    }

    private void fetchServiceAndStylistDetails(String appointmentId, String date, String time, String serviceId, String stylistId) {
        DatabaseReference servicesRef = databaseReference.child("Services").child(serviceId);
        DatabaseReference stylistsRef = databaseReference.child("Stylists").child(stylistId);

        servicesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot serviceSnapshot) {
                String serviceName = serviceSnapshot.child("name").getValue(String.class);

                stylistsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot stylistSnapshot) {
                        String stylistName = stylistSnapshot.child("name").getValue(String.class);

                        pastAppointments.add(new Appointment(date, serviceName, stylistName, time));
                        appointmentIds.add(appointmentId);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("AppointmentHistory", "Error fetching stylist details", error.toException());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("AppointmentHistory", "Error fetching service details", error.toException());
            }
        });
    }

    public void openUserMainPage(View view) {
        Intent intent = new Intent(AppointmentHistory.this, UserMainPageActivity.class);
        startActivity(intent);
    }

    public void openServicesList(View view) {
        Intent intent = new Intent(AppointmentHistory.this, ServiceList.class);
        startActivity(intent);
    }

    public void openStylistList(View view) {
        Intent intent = new Intent(AppointmentHistory.this, ViewStylistList.class);
        startActivity(intent);
    }

    public void openReservationPage (View view) {
        Intent intent = new Intent(AppointmentHistory.this, AppointmentReservationActivity.class);
        startActivity(intent);
    }

    public void openNotificationPage (View view) {
        Intent intent = new Intent(AppointmentHistory.this, AppointmentNotificationActivity.class);
        startActivity(intent);
    }

    public void openProfilePage(View view) {
        Intent intent = new Intent(AppointmentHistory.this, ProfileActivity.class);
        startActivity(intent);
    }

}

