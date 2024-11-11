package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AdminProfile extends AppCompatActivity {
    private ImageView profileImage;
    private TextView valueName, valueBirthday, valueGender, valueContact, valueEmail;
    private Button editProfileButton, logoutAdminButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        profileImage = findViewById(R.id.profileImage);
        valueName = findViewById(R.id.valueName);
        valueBirthday = findViewById(R.id.valueBirthday);
        valueGender = findViewById(R.id.valueGender);
        valueContact = findViewById(R.id.valueContact);
        valueEmail = findViewById(R.id.valueEmail);
        editProfileButton = findViewById(R.id.editProfileButton);
        logoutAdminButton = findViewById(R.id.logoutAdminButton);

        loadUserData();

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProfile.this, AdminEditProfile.class);
                startActivity(intent);
            }
        });

        logoutAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProfile.this, MainActivity.class);
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

    public void openAdminMainPage(View view) {
        Intent intent = new Intent(AdminProfile.this, AdminMainPageActivity.class);
        startActivity(intent);
    }

    public void manageService(View view) {
        Intent intent = new Intent(AdminProfile.this, ManageServiceList.class);
        startActivity(intent);
    }

    public void manageStylist(View view) {
        Intent intent = new Intent(AdminProfile.this, ManageStylist.class);
        startActivity(intent);
    }

    public void manageAppointment(View view) {
        Intent intent = new Intent(AdminProfile.this, ManageAppointment.class);
        startActivity(intent);
    }

    public void openAdminNotification(View view) {
        Intent intent = new Intent(AdminProfile.this, AdminNotificationActivity.class);
        startActivity(intent);
    }

    public void openAdminProfile(View view) {
        Intent intent = new Intent(AdminProfile.this, AdminProfile.class);
        startActivity(intent);
    }
}
