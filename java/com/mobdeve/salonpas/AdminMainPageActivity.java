package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AdminMainPageActivity extends AppCompatActivity {

    private TextView greetingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminmainpage);

        greetingTextView = findViewById(R.id.appointmentHistory);

        greetingTextView.setText("Welcome, Admin!");
    }
    public void openAdminMainPage(View view) {
        Intent intent = new Intent(AdminMainPageActivity.this, AdminMainPageActivity.class);
        startActivity(intent);
    }

    public void manageService(View view) {
        Intent intent = new Intent(AdminMainPageActivity.this, ManageServiceList.class);
        startActivity(intent);
    }

    public void manageStylist(View view) {
        Intent intent = new Intent(AdminMainPageActivity.this, ManageStylist.class);
        startActivity(intent);
    }

    public void manageAppointment(View view) {
        Intent intent = new Intent(AdminMainPageActivity.this, ManageAppointment.class);
        startActivity(intent);
    }

    public void openAdminNotification(View view) {
        Intent intent = new Intent(AdminMainPageActivity.this, AdminNotificationActivity.class);
        startActivity(intent);
    }

    public void openAdminProfile(View view) {
        Intent intent = new Intent(AdminMainPageActivity.this, AdminProfile.class);
        startActivity(intent);
    }
}
