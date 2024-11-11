package com.mobdeve.salonpas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentList;
    private OnAppointmentClickListener onAppointmentClickListener;

    public interface OnAppointmentClickListener {
        void onAppointmentClick(Appointment appointment);
    }

    public AppointmentAdapter(List<Appointment> appointmentList, OnAppointmentClickListener listener) {
        this.appointmentList = appointmentList;
        this.onAppointmentClickListener = listener;
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
        holder.appointmentDate.setText(appointment.getDate());
        holder.serviceDescription.setText(appointment.getServices());

        holder.itemView.setOnClickListener(v -> onAppointmentClickListener.onAppointmentClick(appointment));

        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), UserEditAppointmentActivity.class);
            intent.putExtra("appointment_date", appointment.getDate());
            intent.putExtra("appointment_services", appointment.getServices());
            intent.putExtra("appointment_stylist", appointment.getStylist());
            v.getContext().startActivity(intent);
        });

        holder.cancelButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), UserCancelAppointmentActivity.class);
            intent.putExtra("appointment_date", appointment.getDate());
            intent.putExtra("appointment_services", appointment.getServices());
            intent.putExtra("appointment_stylist", appointment.getStylist());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView appointmentDate;
        TextView serviceDescription;
        Button editButton;
        Button cancelButton;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            appointmentDate = itemView.findViewById(R.id.appointmentDate);
            serviceDescription = itemView.findViewById(R.id.serviceDescription);
            editButton = itemView.findViewById(R.id.editButton);
            cancelButton = itemView.findViewById(R.id.cancelButton);
        }
    }
}
