package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserCancelAppointmentActivity extends AppCompatActivity {

    private EditText cancelReason;
    private TextView appointmentDetails;

    private String appointmentId;
    private String appointmentDate;
    private String appointmentServices;
    private String appointmentStylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cancel_appointment);

        cancelReason = findViewById(R.id.cancelReason);
        appointmentDetails = findViewById(R.id.appointmentDetails);

        appointmentId = getIntent().getStringExtra("appointment_id");
        appointmentDate = getIntent().getStringExtra("appointment_date");
        appointmentServices = getIntent().getStringExtra("appointment_services");
        appointmentStylist = getIntent().getStringExtra("appointment_stylist");

        appointmentDetails.setText(
                "Appointment ID: " + appointmentId + "\n" +
                        "Date: " + appointmentDate + "\n" +
                        "Services: " + appointmentServices + "\n" +
                        "Stylist: " + appointmentStylist
        );
    }

    public void confirmCancellation(View view) {
        String reason = cancelReason.getText().toString().trim();

        if (!reason.isEmpty()) {

            Toast.makeText(this, "Appointment canceled", Toast.LENGTH_SHORT).show();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("cancel_reason", reason);
            setResult(RESULT_OK, resultIntent);

            finish();
        } else {
            Toast.makeText(this, "Please provide a cancellation reason", Toast.LENGTH_SHORT).show();
        }
    }
}
