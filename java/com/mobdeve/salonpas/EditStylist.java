package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditStylist extends AppCompatActivity {
    private TextView nameTextView;
    private TextView ratingTextView;
    private TextView experienceTextView;
    private TextView servicesTextView;
    private ImageView photoImageView;

    private String name;
    private double rating;
    private int years;
    private String services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylist_profile);

        name = getIntent().getStringExtra("stylist_name");
        String photo = getIntent().getStringExtra("stylist_photo");
        years = getIntent().getIntExtra("stylist_experience", 0);
        rating = getIntent().getDoubleExtra("stylist_rating", 0);
        services = getIntent().getStringExtra("stylist_services");

        nameTextView = findViewById(R.id.stylistName);
        photoImageView = findViewById(R.id.stylistImage);
        ratingTextView = findViewById(R.id.stylistRating);
        experienceTextView = findViewById(R.id.stylistExperience);
        servicesTextView = findViewById(R.id.stylistServices);

        updateViews();

        int photoResId = getResources().getIdentifier(photo, "drawable", getPackageName());
            photoImageView.setImageResource(photoResId);

        setEditableOnClick(nameTextView, true);
        setEditableOnClick(ratingTextView, true);
        setEditableOnClick(experienceTextView, true);
        setEditableOnClick(servicesTextView, true);
    }

    private void updateViews() {
        nameTextView.setText(name);
        ratingTextView.setText(String.format("%.1f", rating));
        experienceTextView.setText(String.format("%d", years));
        servicesTextView.setText(services);
    }

    private void setEditableOnClick(TextView textView, boolean isEditable) {
        textView.setOnClickListener(v -> {
            EditText editText = new EditText(EditStylist.this);
            editText.setText(textView.getText().toString());
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            editText.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            ViewGroup parent = (ViewGroup) textView.getParent();
            if (parent != null) {
                LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                editText.setLayoutParams(params);

                int index = parent.indexOfChild(textView);
                parent.addView(editText, index);
                textView.setVisibility(View.GONE);

                editText.requestFocus();
                editText.setOnFocusChangeListener((v1, hasFocus) -> {
                    if (!hasFocus) {
                        if (isEditable) {
                            if (textView == nameTextView) {
                                name = editText.getText().toString();
                            } else if (textView == servicesTextView) {
                                services = editText.getText().toString();
                            } else if (textView == ratingTextView) {
                                rating = Double.parseDouble(editText.getText().toString());
                            } else if (textView == experienceTextView) {
                                years = Integer.parseInt(editText.getText().toString());
                            }
                        }

                        updateViews();
                        parent.removeView(editText);
                        textView.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    public void openAdminMainPage(View view) {
        Intent intent = new Intent(EditStylist.this, AdminMainPageActivity.class);
        startActivity(intent);
    }

    public void manageService(View view) {
        Intent intent = new Intent(EditStylist.this, ManageServiceList.class);
        startActivity(intent);
    }

    public void manageStylist(View view) {
        Intent intent = new Intent(EditStylist.this, ManageStylist.class);
        startActivity(intent);
    }

    public void manageAppointment(View view) {
        Intent intent = new Intent(EditStylist.this, ManageAppointment.class);
        startActivity(intent);
    }
}