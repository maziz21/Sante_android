package com.example.sante;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sante.R;


import java.util.ArrayList;
import java.util.List;

public class RendezVousAdapter extends RecyclerView.Adapter<RendezVousAdapter.RdvViewHolder> {

    public interface OnDeleteClickListener {
        void onDeleteClick(int id);
    }

    private List<RendezVous> data = new ArrayList<>();
    private OnDeleteClickListener deleteListener;

    public void setData(List<RendezVous> newData) {
        data = newData;
        notifyDataSetChanged();
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteListener = listener;
    }

    @NonNull
    @Override
    public RdvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rendezvous, parent, false);
        return new RdvViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RdvViewHolder holder, int position) {
        RendezVous rdv = data.get(position);
        holder.tvDate.setText(rdv.date);
        holder.tvHeure.setText(rdv.heure);
        holder.tvMedecin.setText(rdv.medecin);
        holder.tvLieu.setText(rdv.lieu);

        holder.btnSupprimer.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onDeleteClick(rdv.id); // on transmet l’ID
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class RdvViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvHeure, tvMedecin, tvLieu;
        Button btnSupprimer;

        public RdvViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvHeure = itemView.findViewById(R.id.tvHeure);
            tvMedecin = itemView.findViewById(R.id.tvMedecin);
            tvLieu = itemView.findViewById(R.id.tvLieu);
            btnSupprimer = itemView.findViewById(R.id.btnSupprimer);
        }
    }
}
