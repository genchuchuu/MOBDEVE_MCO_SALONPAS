package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewStylistList extends AppCompatActivity {

    private List<Stylist> stylistList = new ArrayList<>();
    private List<LinearLayout> stylistViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_stylist);

        SearchView stylistSearch = findViewById(R.id.searchView);
        Spinner searchTypeSpinner = findViewById(R.id.spinner_search_type);

        ArrayAdapter<String> searchTypeAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, new String[]{"Name", "Rating", "Services"});
        searchTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(searchTypeAdapter);

        initializeStylists();

        setupStylistViews();

        stylistSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterStylistList(query, searchTypeSpinner.getSelectedItem().toString());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterStylistList(newText, searchTypeSpinner.getSelectedItem().toString());
                return false;
            }
        });
    }

    private void initializeStylists() {
        stylistList.add(new Stylist("Hirai Momo", "stylist1", 10, 5, "Hair cut, Hair style, Hair color, Hair treatment"));
        stylistList.add(new Stylist("Jeon Wonwoo", "stylist2", 10, 5, "Hair cut, Hair style, Hair color, Hair treatment"));
        stylistList.add(new Stylist("Miyawaki Sakura", "stylist3", 3, 4, "Hair cut, Hair treatment"));
        stylistList.add(new Stylist("Sabrina Carpenter", "stylist4", 2, 3, "Hair cut, Hair color, Hair treatment"));
        stylistList.add(new Stylist("Olivia Rodrigo", "stylist5", 2, 3, "Hair style, Hair treatment"));
    }

    private void setupStylistViews() {
        LinearLayout stylist1 = findViewById(R.id.appointmentView1);
        stylistViews.add(stylist1);
        stylist1.setOnClickListener(v -> openStylistProfile(stylistList.get(0)));

        LinearLayout stylist2 = findViewById(R.id.appointmentView2);
        stylistViews.add(stylist2);
        stylist2.setOnClickListener(v -> openStylistProfile(stylistList.get(1)));

        LinearLayout stylist3 = findViewById(R.id.stylistview3);
        stylistViews.add(stylist3);
        stylist3.setOnClickListener(v -> openStylistProfile(stylistList.get(2)));

        LinearLayout stylist4 = findViewById(R.id.stylistview4);
        stylistViews.add(stylist4);
        stylist4.setOnClickListener(v -> openStylistProfile(stylistList.get(3)));

        LinearLayout stylist5 = findViewById(R.id.stylistview5);
        stylistViews.add(stylist5);
        stylist5.setOnClickListener(v -> openStylistProfile(stylistList.get(4)));
    }

    private void openStylistProfile(Stylist stylist) {
        Intent intent = new Intent(this, StylistProfile.class);
        intent.putExtra("stylist_name", stylist.getName());
        intent.putExtra("stylist_photo", stylist.getPhoto());
        intent.putExtra("stylist_experience", stylist.getYearsOfExperience());
        intent.putExtra("stylist_rating", stylist.getRating());
        intent.putExtra("stylist_services", stylist.getServices());
        startActivity(intent);
    }

    private void filterStylistList(String query, String searchType) {
        query = query.toLowerCase();

        for (int i = 0; i < stylistList.size(); i++) {
            Stylist stylist = stylistList.get(i);
            LinearLayout stylistView = stylistViews.get(i);

            boolean matches = false;

            switch (searchType) {
                case "Name":
                    matches = stylist.getName().toLowerCase().contains(query);
                    break;
                case "Rating":
                    matches = String.valueOf(stylist.getRating()).contains(query);
                    break;
                case "Services":
                    matches = stylist.getServices().toLowerCase().contains(query);
                    break;
            }

            if (matches) {
                stylistView.setVisibility(View.VISIBLE);
            } else {
                stylistView.setVisibility(View.GONE);
            }
        }
    }

    public void openUserMainPage(View view) {
        Intent intent = new Intent(ViewStylistList.this, UserMainPageActivity.class);
        startActivity(intent);
    }

    public void openServicesList(View view) {
        Intent intent = new Intent(ViewStylistList.this, ServiceList.class);
        startActivity(intent);
    }

    public void openStylistList(View view) {
        Intent intent = new Intent(ViewStylistList.this, ViewStylistList.class);
        startActivity(intent);
    }

    public void openReservationPage (View view) {
        Intent intent = new Intent(ViewStylistList.this, AppointmentReservationActivity.class);
        startActivity(intent);
    }

    public void openNotificationPage (View view) {
        Intent intent = new Intent(ViewStylistList.this, AppointmentNotificationActivity.class);
        startActivity(intent);
    }

    public void openProfilePage (View view) {
        Intent intent = new Intent(ViewStylistList.this, ProfileActivity.class);
        startActivity(intent);
    }

}
