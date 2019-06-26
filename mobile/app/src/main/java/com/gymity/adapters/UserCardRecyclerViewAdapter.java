package com.gymity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gymity.R;
import com.gymity.model.Users;
import com.gymity.viewholders.UserCardViewHolder;

import java.util.List;

public class UserCardRecyclerViewAdapter extends RecyclerView.Adapter<UserCardViewHolder> {

    private List<Users> users;

    @NonNull
    @Override
    public UserCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shr_user_card, parent, false);
        return new UserCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCardViewHolder holder, int position) {
        if (users != null && position < users.size()) {
            Users user = users.get(position);
            holder.fullName.setText(user.fullName);
            holder.username.setText(user.credentials.username);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
