package com.gymity.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gymity.R;

public class CommentCardViewHolder extends RecyclerView.ViewHolder {
    public TextView commentator;
    public TextView comment;

    public CommentCardViewHolder(@NonNull View itemView) {
        super(itemView);
        commentator = itemView.findViewById(R.id.commentator);
        comment = itemView.findViewById(R.id.comment);
    }
}
