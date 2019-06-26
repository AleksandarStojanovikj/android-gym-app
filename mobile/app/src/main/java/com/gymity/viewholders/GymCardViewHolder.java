package com.gymity.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gymity.R;

public class GymCardViewHolder extends RecyclerView.ViewHolder {

    public TextView gymName;
    public TextView gymLocation;

    public GymCardViewHolder(@NonNull View itemView) {
        super(itemView);
        gymName = itemView.findViewById(R.id.gym_name_text_view);
        gymLocation = itemView.findViewById(R.id.gym_location_text_view);
    }
}
