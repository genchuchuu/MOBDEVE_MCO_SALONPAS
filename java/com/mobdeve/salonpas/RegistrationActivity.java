package com.mobdeve.salonpas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity {

    private EditText firstNameInput, lastNameInput, emailInput, passwordInput, birthdateInput;
    private Button registerButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        birthdateInput = findViewById(R.id.birthdateInput);
        registerButton = findViewById(R.id.registerButton);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        birthdateInput.setOnClickListener(v -> showDatePickerDialog());

        registerButton.setOnClickListener(view -> {
            if (validateInputs()) {
                registerButton.setEnabled(false);  // Disable button to prevent double-clicks
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                String firstName = firstNameInput.getText().toString();
                String lastName = lastNameInput.getText().toString();
                String birthdate = birthdateInput.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(authTask -> {
                            if (authTask.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                User userInfo = new User(firstName, lastName, email, birthdate);

                                mDatabase.child(user.getUid()).setValue(userInfo)
                                        .addOnCompleteListener(dbTask -> {
                                            if (dbTask.isSuccessful()) {
                                                Toast.makeText(RegistrationActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(RegistrationActivity.this, UserMainPageActivity.class));
                                                finish();
                                            } else {
                                                Toast.makeText(RegistrationActivity.this, "Database error: " + dbTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                            registerButton.setEnabled(true);  // Re-enable button
                                        });
                            } else {
                                String errorMessage = authTask.getException() != null ? authTask.getException().getMessage() : "Unknown error";
                                Toast.makeText(RegistrationActivity.this, "Authentication failed: " + errorMessage, Toast.LENGTH_LONG).show();
                                registerButton.setEnabled(true);  // Re-enable button
                            }
                        });
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String birthdate = (selectedMonth + 1) + "/" + selectedDay + "/" + selectedYear;
                    birthdateInput.setText(birthdate);
                },
                year, month, day);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private boolean validateInputs() {
        if (TextUtils.isEmpty(firstNameInput.getText().toString())) {
            firstNameInput.setError("First Name is required!");
            firstNameInput.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(lastNameInput.getText().toString())) {
            lastNameInput.setError("Last Name is required!");
            lastNameInput.requestFocus();
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
        }

        return true;
    }
}
