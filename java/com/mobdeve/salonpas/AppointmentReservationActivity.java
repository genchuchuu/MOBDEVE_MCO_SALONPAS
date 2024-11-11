package com.mobdeve.salonpas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AppointmentReservationActivity extends AppCompatActivity {

    private Spinner spinnerServices, spinnerStylists;
    private Button btnSelectDate, btnSelectTime, btnConfirmBooking;
    private String selectedService, selectedStylist;
    private String selectedDate, selectedTime;
    private TextView textSelectedDate, textSelectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_reservation);

        spinnerServices = findViewById(R.id.spinner_services);
        spinnerStylists = findViewById(R.id.spinner_stylists);
        btnSelectDate = findViewById(R.id.btn_select_date);
        btnSelectTime = findViewById(R.id.btn_select_time);
        btnConfirmBooking = findViewById(R.id.btn_confirm_booking);
        textSelectedDate = findViewById(R.id.text_selected_date);
        textSelectedTime = findViewById(R.id.text_selected_time);

        String[] services = {"Haircut and Trimming", "Hair Color", "Hairstyle for Events", "Hair Treatments"};
        String[] stylists = {"Hirai Momo", "Jeon Wonwoo", "Miyawaki Sakura", "Sabrina Carpenter", "Olivia Rodrigo"};

        ArrayAdapter<String> servicesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, services);
        servicesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerServices.setAdapter(servicesAdapter);

        ArrayAdapter<String> stylistsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stylists);
        stylistsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStylists.setAdapter(stylistsAdapter);

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AppointmentReservationActivity.this,
                        (view, year1, month1, dayOfMonth) -> {
                            selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                            textSelectedDate.setText("Selected Date: " + selectedDate);
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AppointmentReservationActivity.this,
                        (view, hourOfDay, minute1) -> {
                            selectedTime = String.format("%02d:%02d", hourOfDay, minute1);
                            textSelectedTime.setText("Selected Time: " + selectedTime);
                        }, hour, minute, true);
                timePickerDialog.show();
            }
        });

        btnConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedService = spinnerServices.getSelectedItem().toString();
                selectedStylist = spinnerStylists.getSelectedItem().toString();

                if (selectedDate != null && selectedTime != null) {
                    Toast.makeText(AppointmentReservationActivity.this, "Booking Confirmed: " + selectedService + " with " +
                            selectedStylist + " on " + selectedDate + " at " + selectedTime, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AppointmentReservationActivity.this, "Please select both date and time.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void openUserMainPage(View view) {
        Intent intent = new Intent(AppointmentReservationActivity.this, UserMainPageActivity.class);
        startActivity(intent);
    }

    public void openServicesList(View view) {
        Intent intent = new Intent(AppointmentReservationActivity.this, ServiceList.class);
        startActivity(intent);
    }

    public void openStylistList(View view) {
        Intent intent = new Intent(AppointmentReservationActivity.this, ViewStylistList.class);
        startActivity(intent);
    }

    public void openReservationPage (View view) {
        Intent intent = new Intent(AppointmentReservationActivity.this, AppointmentReservationActivity.class);
        startActivity(intent);
    }

    public void openNotificationPage (View view) {
        Intent intent = new Intent(AppointmentReservationActivity.this, AppointmentNotificationActivity.class);
        startActivity(intent);
    }

}
