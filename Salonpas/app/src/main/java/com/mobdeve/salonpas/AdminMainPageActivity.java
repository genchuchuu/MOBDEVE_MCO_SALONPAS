package com.mobdeve.salonpas;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AdminMainPageActivity extends AppCompatActivity {

    private TextView greetingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminmainpage);

        greetingTextView = findViewById(R.id.greeting);

        greetingTextView.setText("Welcome, Admin!");
    }
}
