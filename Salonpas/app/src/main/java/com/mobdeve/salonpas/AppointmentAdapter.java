package com.mobdeve.salonpas;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.time.LocalDate;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentList;
    private List<String> appointmentIdList;

    public AppointmentAdapter(List<Appointment> appointmentList, List<String> appointmentIdList) {
        this.appointmentList = appointmentList;
        this.appointmentIdList = appointmentIdList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_item, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);

        holder.appointmentDate.setText(appointment.getDate() + " at " + appointment.getTime());
        holder.serviceDescription.setText(appointment.getServiceName());
        holder.stylistName.setText(appointment.getStylistName());

        if (isUpcoming(appointment.getDate())) {
            holder.editButton.setVisibility(View.VISIBLE);
            holder.editButton.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), UserEditAppointmentActivity.class);

                intent.putExtra("appointment_id", appointmentIdList.get(position));
                intent.putExtra("appointment_date", appointment.getDate());
                intent.putExtra("appointment_time", appointment.getTime());
                intent.putExtra("appointment_services", appointment.getServiceName());
                intent.putExtra("appointment_stylist", appointment.getStylistName());

                holder.itemView.getContext().startActivity(intent);
            });
        } else {
            holder.editButton.setVisibility(View.GONE);
        }

        if (isUpcoming(appointment.getDate())) {
            holder.cancelButton.setVisibility(View.VISIBLE);
            holder.cancelButton.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), UserCancelAppointmentActivity.class);

                intent.putExtra("appointment_id", appointmentIdList.get(position)); // Fetch ID from ID list
                intent.putExtra("appointment_date", appointment.getDate());
                intent.putExtra("appointment_services", appointment.getServiceName());
                intent.putExtra("appointment_stylist", appointment.getStylistName());
                holder.itemView.getContext().startActivity(intent);
            });
        } else {
            holder.cancelButton.setVisibility(View.GONE);
        }
    }

    private boolean isUpcoming(String appointmentDate) {
        LocalDate date = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date = LocalDate.parse(appointmentDate);
            return date.isAfter(LocalDate.now());
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView appointmentDate;
        TextView serviceDescription;
        TextView stylistName;
        Button editButton;
        Button cancelButton;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            appointmentDate = itemView.findViewById(R.id.appointmentDate);
            serviceDescription = itemView.findViewById(R.id.serviceDescription);
            stylistName = itemView.findViewById(R.id.stylistName);
            editButton = itemView.findViewById(R.id.editButton);
            cancelButton = itemView.findViewById(R.id.cancelButton);
        }
    }
}
