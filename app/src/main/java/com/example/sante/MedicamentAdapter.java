package com.example.sante;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sante.MedicamentResponse;

import java.util.ArrayList;
import java.util.List;

public class MedicamentAdapter extends RecyclerView.Adapter<MedicamentAdapter.ViewHolder> {

    private List<MedicamentResponse.Medicament> medicaments = new ArrayList<>();
    private OnItemClickListener listener;

    public void setMedicaments(List<MedicamentResponse.Medicament> medicaments) {
        this.medicaments = medicaments;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medicament, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MedicamentResponse.Medicament medicament = medicaments.get(position);

        String nom = medicament.openFDA.brandName != null ? medicament.openFDA.brandName.get(0) : "Inconnu";
        String usage = (medicament.indications != null && !medicament.indications.isEmpty())
                ? medicament.indications.get(0)
                : "Aucune indication disponible";

        holder.tvNom.setText(nom);
        holder.tvIndications.setText(usage);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(medicament);
        });
    }

    @Override
    public int getItemCount() {
        return medicaments.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNom, tvIndications;

        ViewHolder(View itemView) {
            super(itemView);
            tvNom = itemView.findViewById(R.id.tvNom);
            tvIndications = itemView.findViewById(R.id.tvIndications);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MedicamentResponse.Medicament medicament);
    }
}
