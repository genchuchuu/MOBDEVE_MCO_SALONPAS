package com.mobdeve.salonpas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ManageServiceList extends AppCompatActivity implements ServiceAdapter.OnServiceClickListener {

    private RecyclerView serviceRecyclerView;
    private ServiceAdapter serviceAdapter;
    private List<Service> serviceList;
    private Button addServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_service_list);

        serviceRecyclerView = findViewById(R.id.ServiceListRecView);
        addServiceButton = findViewById(R.id.addServiceButton);

        serviceList = new ArrayList<>();
        serviceList.add(new Service("Hair Cut", "Basic haircut", "30 mins", "$15", R.drawable.haircut));
        serviceList.add(new Service("Hair Color", "Hair coloring", "2 hours", "$60", R.drawable.haircolor));
        serviceList.add(new Service("Hair Style", "Hair styling", "45 mins", "$25", R.drawable.hairstyle));
        serviceList.add(new Service("Hair Treatment", "Hair treatment", "1 hour", "$50", R.drawable.hairtreatment));

        serviceAdapter = new ServiceAdapter(serviceList, this);
        serviceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        serviceRecyclerView.setAdapter(serviceAdapter);

        addServiceButton.setOnClickListener(v -> showAddServiceDialog());
    }

    @Override
    public void onServiceClick(Service service) {
        showEditServiceDialog(service);
    }

    private void showEditServiceDialog(Service service) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Service");

        View view = getLayoutInflater().inflate(R.layout.dialogue_edit_service, null);
        builder.setView(view);

        TextInputEditText nameInput = view.findViewById(R.id.EditSrvName);
        TextInputEditText descInput = view.findViewById(R.id.EditSrvDesc);
        TextInputEditText durationInput = view.findViewById(R.id.EditSrvDuration);
        TextInputEditText priceInput = view.findViewById(R.id.EditSrvPrice);
        ImageView serviceImage = view.findViewById(R.id.EditSrvImg);

        nameInput.setText(service.getName());
        descInput.setText(service.getDescription());
        durationInput.setText(service.getDuration());
        priceInput.setText(service.getPrice());
        serviceImage.setImageResource(service.getImage());

        builder.setPositiveButton("Save", (dialog, which) -> {

            service.setName(nameInput.getText().toString());
            service.setDescription(descInput.getText().toString());
            service.setDuration(durationInput.getText().toString());
            service.setPrice(priceInput.getText().toString());
            serviceAdapter.notifyDataSetChanged();
        });

        builder.setNeutralButton("Delete", (dialog, which) -> {
            showDeleteConfirmationDialog(service);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    private void showDeleteConfirmationDialog(Service service) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Service")
                .setMessage("Are you sure you want to delete this service?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    serviceList.remove(service);
                    serviceAdapter.notifyDataSetChanged();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void showAddServiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Service");

        View view = getLayoutInflater().inflate(R.layout.dialogue_add_service, null);
        builder.setView(view);


        TextInputEditText nameInput = view.findViewById(R.id.AddSrvName);
        TextInputEditText descInput = view.findViewById(R.id.AddSrvDesc);
        TextInputEditText durationInput = view.findViewById(R.id.AddSrvDuration);
        TextInputEditText priceInput = view.findViewById(R.id.AddSrvPrice);
        ImageView serviceImage = view.findViewById(R.id.AddSrvImg);

        builder.setPositiveButton("Add", (dialog, which) -> {

            Service newService = new Service(
                    nameInput.getText().toString(),
                    descInput.getText().toString(),
                    durationInput.getText().toString(),
                    priceInput.getText().toString(),
                    R.drawable.haircolor
            );
            serviceList.add(newService);
            serviceAdapter.notifyItemInserted(serviceList.size() - 1);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }
    public void openAdminMainPage(View view) {
        Intent intent = new Intent(ManageServiceList.this, AdminMainPageActivity.class);
        startActivity(intent);
    }

    public void manageService(View view) {
        Intent intent = new Intent(ManageServiceList.this, ManageServiceList.class);
        startActivity(intent);
    }

    public void manageStylist(View view) {
        Intent intent = new Intent(ManageServiceList.this, ManageStylist.class);
        startActivity(intent);
    }

    public void manageAppointment(View view) {
        Intent intent = new Intent(ManageServiceList.this, ManageAppointment.class);
        startActivity(intent);
    }

    public void openAdminNotification(View view) {
        Intent intent = new Intent(ManageServiceList.this, AdminNotificationActivity.class);
        startActivity(intent);
    }

    public void openAdminProfile(View view) {
        Intent intent = new Intent(ManageServiceList.this, AdminProfile.class);
        startActivity(intent);
    }
}

