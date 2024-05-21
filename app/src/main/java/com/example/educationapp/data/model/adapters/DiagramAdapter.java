package com.example.educationapp.data.model.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapp.R;
import com.example.educationapp.data.model.DiagramItem;

import java.util.List;

public class DiagramAdapter extends RecyclerView.Adapter<DiagramAdapter.DiagramViewHolder> {

    private List<DiagramItem> diagramItems;

    public DiagramAdapter(List<DiagramItem> diagramItems) {
        this.diagramItems = diagramItems;
    }

    @NonNull
    @Override
    public DiagramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diagramm, parent, false);
        return new DiagramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiagramViewHolder holder, int position) {
        DiagramItem item = diagramItems.get(position);
        holder.label.setText(item.getLabel());
        holder.value.setText(String.valueOf(item.getValue()));

        int positiveValue = item.getValue() > 0 ? item.getValue() : 0;
        int negativeValue = item.getValue() < 0 ? -item.getValue() : 0;

        holder.negativeBar.setLayoutParams(new LinearLayout.LayoutParams(0, 45, 10 - negativeValue));
        holder.positiveBar.setLayoutParams(new LinearLayout.LayoutParams(0, 45, 10 - positiveValue));
    }

    @Override
    public int getItemCount() {
        return diagramItems.size();
    }

    static class DiagramViewHolder extends RecyclerView.ViewHolder {

        TextView label;
        View negativeBar;
        View zeroBar;
        View positiveBar;
        TextView value;

        public DiagramViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.label);
            negativeBar = itemView.findViewById(R.id.negativeBar);
            zeroBar = itemView.findViewById(R.id.zeroBar);
            positiveBar = itemView.findViewById(R.id.positiveBar);
            value = itemView.findViewById(R.id.value);
        }
    }
}
