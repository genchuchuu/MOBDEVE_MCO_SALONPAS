package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class UserMainPageActivity extends AppCompatActivity {

    private TextView greetingTextView;
    private TextView faqsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermainpage);

        greetingTextView = findViewById(R.id.appointmentHistpry);

        String firstName = getIntent().getStringExtra("firstName");
        if (firstName != null && !firstName.isEmpty()) {
            greetingTextView.setText("Hello, " + firstName + "!");
            Toast.makeText(UserMainPageActivity.this, "Hello, " + firstName + "!", Toast.LENGTH_SHORT).show();
        } else {
            greetingTextView.setText("Hello, User!");
        }

        faqsTextView = findViewById(R.id.faqs);

        String faqText = "<b>1. What is the Salonpas Hair Salon app?</b><br/>" +
                "The Salonpas app is a salon appointment scheduling platform that allows clients to register, book appointments, view services and stylists, and manage their salon experience. It's available for both Android and iOS devices.<br/><br/>" +
                "<b>2. What services are available through the app?</b><br/>" +
                "Clients can book hair-related services such as haircuts, coloring, styling, hair treatments, and extensions. Full details, including prices, duration, and available stylists, are shown for each service.<br/><br/>" +
                "<b>3. How can I book an appointment?</b><br/>" +
                "Once registered, you can select a service, choose a preferred stylist, and book a time slot that fits your schedule. You will receive a notification 30 minutes before your appointment.<br/><br/>" +
                "<b>4. Can I choose my stylist?</b><br/>" +
                "Yes, the app allows you to filter and view stylists based on their expertise, availability, and reviews. This ensures that you can choose the right stylist for the service you need.<br/><br/>" +
                "<b>5. What happens if I can’t make it to my appointment?</b><br/>" +
                "You can reschedule or cancel your appointment through the app by selecting your booking and providing a reason for the change. You must do this before the scheduled time.<br/><br/>" +
                "<b>6. How do I leave a review?</b><br/>" +
                "You will receive a notification 24 hours after your appointment, prompting you to leave a review for both the service and the stylist. Reviews help other clients in choosing their stylists and can be seen by everyone.";

        faqsTextView.setText(Html.fromHtml(faqText, Html.FROM_HTML_MODE_LEGACY));
    }

    public void openUserMainPage(View view) {
        Intent intent = new Intent(UserMainPageActivity.this, UserMainPageActivity.class);
        startActivity(intent);
    }

    public void openServicesList(View view) {
        Intent intent = new Intent(UserMainPageActivity.this, ServiceList.class);
        startActivity(intent);
    }

    public void openStylistList(View view) {
        Intent intent = new Intent(UserMainPageActivity.this, ViewStylistList.class);
        startActivity(intent);
    }

    public void openReservationPage (View view) {
        Intent intent = new Intent(UserMainPageActivity.this, AppointmentReservationActivity.class);
        startActivity(intent);
    }

    public void openNotificationPage (View view) {
        Intent intent = new Intent(UserMainPageActivity.this, AppointmentNotificationActivity.class);
        startActivity(intent);
    }

    public void openProfilePage (View view) {
        Intent intent = new Intent(UserMainPageActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

}

