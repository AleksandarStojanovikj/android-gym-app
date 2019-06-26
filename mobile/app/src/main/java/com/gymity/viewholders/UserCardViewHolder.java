package com.gymity.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gymity.R;

public class UserCardViewHolder extends RecyclerView.ViewHolder {

    public TextView fullName;
    public TextView username;

    public UserCardViewHolder(@NonNull View itemView) {
        super(itemView);
        fullName = itemView.findViewById(R.id.user_fullName_text_view);
        fullName = itemView.findViewById(R.id.user_username_text_view);
    }
}
