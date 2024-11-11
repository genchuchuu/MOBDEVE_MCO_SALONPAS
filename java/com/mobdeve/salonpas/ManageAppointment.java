package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ManageAppointment extends AppCompatActivity {

    private List<Appointment> pastAppointments;
    private RecyclerView recyclerView;
    private AppointmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_appointment);

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
        Intent intent = new Intent(this, EditAppointment.class);
        intent.putExtra("appointment_date", appointment.getDate());
        intent.putExtra("appointment_services", appointment.getServices());
        intent.putExtra("appointment_stylist", appointment.getStylist());
        startActivity(intent);
    }

    public void openAdminMainPage(View view) {
        Intent intent = new Intent(ManageAppointment.this, AdminMainPageActivity.class);
        startActivity(intent);
    }

    public void manageService(View view) {
        Intent intent = new Intent(ManageAppointment.this, ManageServiceList.class);
        startActivity(intent);
    }

    public void manageStylist(View view) {
        Intent intent = new Intent(ManageAppointment.this, ManageStylist.class);
        startActivity(intent);
    }

    public void manageAppointment(View view) {
        Intent intent = new Intent(ManageAppointment.this, ManageAppointment.class);
        startActivity(intent);
    }

    public void openAdminNotification(View view) {
        Intent intent = new Intent(ManageAppointment.this, AdminNotificationActivity.class);
        startActivity(intent);
    }

    public void openAdminProfile(View view) {
        Intent intent = new Intent(ManageAppointment.this, AdminProfile.class);
        startActivity(intent);
    }
}