package com.gymity.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gymity.R;

public class OfferCardViewHolder extends RecyclerView.ViewHolder {

    public TextView offerPrice;
    public TextView offerGym;
    public TextView offerDescription;

    public OfferCardViewHolder(@NonNull View itemView) {
        super(itemView);
        offerPrice = itemView.findViewById(R.id.offer_price_text_view);
        offerGym = itemView.findViewById(R.id.offer_gym_text_view);
        offerDescription = itemView.findViewById(R.id.offer_description_text_view);
    }
}
