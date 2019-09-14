package com.gymity.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.gymity.R;

public class MyGymCardViewHolder extends RecyclerView.ViewHolder {
    public TextView gymName;
    public TextView gymLocation;
    public MaterialButton unsubscribeFromGym;

    public MyGymCardViewHolder(@NonNull View itemView) {
        super(itemView);
        gymName = itemView.findViewById(R.id.gym_name_text_view);
        gymLocation = itemView.findViewById(R.id.gym_location_text_view);
        unsubscribeFromGym = itemView.findViewById(R.id.unsubscribe_from_gym_button);
    }
}
