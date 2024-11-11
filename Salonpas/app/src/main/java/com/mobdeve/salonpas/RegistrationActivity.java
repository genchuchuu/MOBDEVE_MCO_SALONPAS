package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText fullNameInput, emailInput, passwordInput, birthdateInput;
    private Button registerButton;
    private TextView alreadyHaveAccount, togglePasswordVisibility;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        fullNameInput = findViewById(R.id.fullNameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        birthdateInput = findViewById(R.id.birthdateInput);
        registerButton = findViewById(R.id.registerButton);
        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInputs()) {
                    String fullName = fullNameInput.getText().toString();
                    String firstName = fullName.split(" ")[0];

                    Toast.makeText(RegistrationActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegistrationActivity.this, UserMainPageActivity.class);
                    intent.putExtra("firstName", firstName);
                    startActivity(intent);
                    finish();
                }
            }
        });

        togglePasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPasswordVisible) {
                    passwordInput.setInputType(129);
                    isPasswordVisible = false;
                } else {
                    passwordInput.setInputType(1);
                    isPasswordVisible = true;
                }
                passwordInput.setSelection(passwordInput.getText().length());
            }
        });
    }

    private boolean validateInputs() {
        if (TextUtils.isEmpty(fullNameInput.getText().toString())) {
            fullNameInput.setError("Full Name is required!");
            fullNameInput.requestFocus();
            return false;
        }

        String email = emailInput.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailInput.setError("Email is required!");
            emailInput.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.contains(".com")) {
            emailInput.setError("Enter a valid email (must contain @ and .com)");
            emailInput.requestFocus();
            return false;
        }

        String password = passwordInput.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordInput.setError("Password is required!");
            passwordInput.requestFocus();
            return false;
        } else if (password.length() < 8 || password.length() > 16) {
            passwordInput.setError("Password must be 8-16 characters long");
            passwordInput.requestFocus();
            return false;
        } else if (!password.matches(".*[!@#$%^&*+=?-].*")) {
            passwordInput.setError("Password must contain at least one special character");
            passwordInput.requestFocus();
            return false;
        }

        String birthdate = birthdateInput.getText().toString();
        if (TextUtils.isEmpty(birthdate)) {
            birthdateInput.setError("Birthdate is required!");
            birthdateInput.requestFocus();
            return false;
        } else if (!birthdate.matches("(0[1-9]|1[0-2])/([0-2][0-9]|3[01])/(19|20)\\d{2}")) {
            birthdateInput.setError("Birthdate must be in MM/DD/YYYY format");
            birthdateInput.requestFocus();
            return false;
        }

        return true;
    }
}
