package com.gymity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gymity.R;
import com.gymity.model.Comment;
import com.gymity.viewholders.CommentCardViewHolder;

import java.util.List;

public class CommentCardRecyclerViewAdapter extends RecyclerView.Adapter<CommentCardViewHolder> {

    private Context context;
    private  List<Comment> comments;

    public CommentCardRecyclerViewAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shr_comment_card, parent, false);
        context = parent.getContext();
        return new CommentCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentCardViewHolder holder, final int position) {
        if (comments != null && position < comments.size()) {
            final Comment comment = comments.get(position);
            holder.commentator.setText(comment.getUser().credentials.username);
            holder.comment.setText(comment.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

}
