package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton;
    private TextView togglePasswordVisibility;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginPageButton);
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (email.equals("admin1@salonpas.store.com") && password.equals("Admin_123")) {
                    navigateToAdminPage();
                } else if (email.equals("preciouspaulanicole@gmail.com") && password.equals("pr3c10us")) {
                    navigateToMainPage("Precious");
                } else if (email.equals("maxxieanderson123@gmail.com") && password.equals("MaxxieWinner3")) {
                    navigateToMainPage("Maxxie");
                } else if (email.equals("CaptivatingKatKat@gmail.com") && password.equals("CaptivateK4t")) {
                    navigateToMainPage("KatKat");
                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect email or password!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        togglePasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPasswordVisible) {
                    passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    togglePasswordVisibility.setText("Show");
                    isPasswordVisible = false;
                } else {
                    passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    togglePasswordVisibility.setText("Hide");
                    isPasswordVisible = true;
                }
                passwordInput.setSelection(passwordInput.getText().length());
            }
        });
    }

    private void navigateToMainPage(String firstName) {
        Intent intent = new Intent(LoginActivity.this, UserMainPageActivity.class);
        intent.putExtra("firstName", firstName);
        startActivity(intent);
        finish();
    }

    private void navigateToAdminPage() {
        Intent intent = new Intent(LoginActivity.this, AdminMainPageActivity.class);
        startActivity(intent);
        finish();
    }
}
