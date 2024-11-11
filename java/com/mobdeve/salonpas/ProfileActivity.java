package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private ImageView profileImage;
    private TextView valueName, valueBirthday, valueGender, valueContact, valueEmail;
    private Button editProfileButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImage = findViewById(R.id.profileImage);
        valueName = findViewById(R.id.valueName);
        valueBirthday = findViewById(R.id.valueBirthday);
        valueGender = findViewById(R.id.valueGender);
        valueContact = findViewById(R.id.valueContact);
        valueEmail = findViewById(R.id.valueEmail);
        editProfileButton = findViewById(R.id.editProfileButton);
        logoutButton = findViewById(R.id.logoutButton);

        loadUserData();

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadUserData() {
        String name = "Kang Seulgi";
        String birthday = "01/01/1991";
        String gender = "Female";
        String contact = "1234567890";
        String email = "k.seulgi@gmail.com";

        valueName.setText(name);
        valueBirthday.setText(birthday);
        valueGender.setText(gender);
        valueContact.setText(contact);
        valueEmail.setText(email);
    }

    public void openAppointmentHistory(View view) {
        Intent intent = new Intent(ProfileActivity.this, AppointmentHistory.class);
        startActivity(intent);
    }

    public void openUserMainPage(View view) {
        Intent intent = new Intent(ProfileActivity.this, UserMainPageActivity.class);
        startActivity(intent);
    }

    public void openServicesList(View view) {
        Intent intent = new Intent(ProfileActivity.this, ServiceList.class);
        startActivity(intent);
    }

    public void openStylistList(View view) {
        Intent intent = new Intent(ProfileActivity.this, ViewStylistList.class);
        startActivity(intent);
    }

    public void openReservationPage(View view) {
        Intent intent = new Intent(ProfileActivity.this, AppointmentReservationActivity.class);
        startActivity(intent);
    }

    public void openNotificationPage(View view) {
        Intent intent = new Intent(ProfileActivity.this, AppointmentNotificationActivity.class);
        startActivity(intent);
    }

    public void openProfilePage(View view) {
        Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}
