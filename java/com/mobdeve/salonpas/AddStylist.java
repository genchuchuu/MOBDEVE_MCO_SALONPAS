package com.mobdeve.salonpas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddStylist extends AppCompatActivity {
    private Button submitStylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_stylist);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        submitStylist = findViewById(R.id.submitStylist);

        submitStylist.setOnClickListener(v -> showCompleteDialog());
    }

    private void showCompleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddStylist.this);
        builder.setTitle("Stylist Added");
        builder.setMessage("The stylist has been successfully added.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void openAdminMainPage(View view) {
        Intent intent = new Intent(AddStylist.this, AdminMainPageActivity.class);
        startActivity(intent);
    }

    public void manageService(View view) {
        Intent intent = new Intent(AddStylist.this, ManageServiceList.class);
        startActivity(intent);
    }

    public void manageStylist(View view) {
        Intent intent = new Intent(AddStylist.this, ManageStylist.class);
        startActivity(intent);
    }

    public void manageAppointment(View view) {
        Intent intent = new Intent(AddStylist.this, ManageAppointment.class);
        startActivity(intent);
    }
    public void openAdminProfile(View view) {
        Intent intent = new Intent(AddStylist.this, AdminProfile.class);
        startActivity(intent);
    }
}