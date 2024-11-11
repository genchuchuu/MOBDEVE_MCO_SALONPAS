package com.mobdeve.salonpas;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserEditAppointmentActivity extends AppCompatActivity {

    private EditText editDate, editServices, editStylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_appointment);

        editDate = findViewById(R.id.editDate);
        editServices = findViewById(R.id.editServices);
        editStylist = findViewById(R.id.editStylist);

        editDate.setText(getIntent().getStringExtra("appointment_date"));
        editServices.setText(getIntent().getStringExtra("appointment_services"));
        editStylist.setText(getIntent().getStringExtra("appointment_stylist"));
    }

    public void saveChanges(View view) {
        String updatedDate = editDate.getText().toString();
        String updatedServices = editServices.getText().toString();
        String updatedStylist = editStylist.getText().toString();

        Toast.makeText(this, "Appointment updated successfully", Toast.LENGTH_SHORT).show();

        finish();
    }
}
