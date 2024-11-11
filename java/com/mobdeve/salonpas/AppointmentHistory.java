package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AppointmentHistory extends AppCompatActivity {

    private List<Appointment> pastAppointments;
    private RecyclerView recyclerView;
    private AppointmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_history);

        recyclerView = findViewById(R.id.appointmentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initializePastAppointments();

        adapter = new AppointmentAdapter(pastAppointments, this::openPastAppointment);
        recyclerView.setAdapter(adapter);
    }

    private void initializePastAppointments() {
        pastAppointments = new ArrayList<>();

        pastAppointments.add(new Appointment("2023-10-05", "Hair cut, Hair color", "Hirai Momo"));
        pastAppointments.add(new Appointment("2023-09-25", "Hair style, Hair treatment", "Jeon Wonwoo"));
        pastAppointments.add(new Appointment("2023-08-15", "Hair treatment", "Miyawaki Sakura"));
    }

    private void openPastAppointment(Appointment appointment) {
        Intent intent = new Intent(this, PastAppointment.class);
        intent.putExtra("appointment_date", appointment.getDate());
        intent.putExtra("appointment_services", appointment.getServices());
        intent.putExtra("appointment_stylist", appointment.getStylist());
        startActivity(intent);
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

}

