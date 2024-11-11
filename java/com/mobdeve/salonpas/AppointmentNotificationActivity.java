package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AppointmentNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_notification);


        String notificationMessage1 = "Your appointment with Hirai Momo is in 30 minutes.";
        String notificationTime1 = "Scheduled for: 3:00 PM";

        String notificationMessage2 = "Your appointment with Jeon Wonwoo has finished.";
        String notificationTime2 = "Finished at: 3:30 PM";

        TextView notificationMessageView1 = findViewById(R.id.notificationMessage1);
        TextView notificationTimeView1 = findViewById(R.id.notificationTime1);

        TextView notificationMessageView2 = findViewById(R.id.notificationMessage2);
        TextView notificationTimeView2 = findViewById(R.id.notificationTime2);

        notificationMessageView1.setText(notificationMessage1);
        notificationTimeView1.setText(notificationTime1);

        notificationMessageView2.setText(notificationMessage2);
        notificationTimeView2.setText(notificationTime2);
    }
    public void openUserMainPage(View view) {
        Intent intent = new Intent(AppointmentNotificationActivity.this, UserMainPageActivity.class);
        startActivity(intent);
    }

    public void openServicesList(View view) {
        Intent intent = new Intent(AppointmentNotificationActivity.this, ServiceList.class);
        startActivity(intent);
    }

    public void openStylistList(View view) {
        Intent intent = new Intent(AppointmentNotificationActivity.this, ViewStylistList.class);
        startActivity(intent);
    }

    public void openReservationPage (View view) {
        Intent intent = new Intent(AppointmentNotificationActivity.this, AppointmentReservationActivity.class);
        startActivity(intent);
    }

    public void openNotificationPage (View view) {
        Intent intent = new Intent(AppointmentNotificationActivity.this, AppointmentNotificationActivity.class);
        startActivity(intent);
    }

    public void openProfilePage (View view) {
        Intent intent = new Intent(AppointmentNotificationActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

}
