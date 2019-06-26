package com.gymity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gymity.R;
import com.gymity.model.Gym;
import com.gymity.viewholders.GymCardViewHolder;

import java.util.List;

public class GymCardRecyclerViewAdapter extends RecyclerView.Adapter<GymCardViewHolder> {

    private List<Gym> gyms;

    @NonNull
    @Override
    public GymCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shr_gym_card, parent, false);
        return new GymCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull GymCardViewHolder holder, int position) {
        if (gyms != null && position < gyms.size()) {
            Gym gym = gyms.get(position);
            holder.gymName.setText(gym.name);
            holder.gymLocation.setText("Located: " + gym.location);
        }
    }

    @Override
    public int getItemCount() {
        return gyms.size();
    }
}
