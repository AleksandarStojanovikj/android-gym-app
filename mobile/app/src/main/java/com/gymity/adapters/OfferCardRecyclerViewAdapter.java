package com.gymity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gymity.R;
import com.gymity.model.OfferDto;
import com.gymity.viewholders.OfferCardViewHolder;

import java.util.List;

public class OfferCardRecyclerViewAdapter extends RecyclerView.Adapter<OfferCardViewHolder> {

    private List<OfferDto> offers;
    private int screenWidth = 0;

    public OfferCardRecyclerViewAdapter(List<OfferDto> offers) {
        this.offers = offers;
    }

    @NonNull
    @Override
    public OfferCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shr_offer_card, parent, false);
        return new OfferCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferCardViewHolder holder, int position) {
        if(offers != null && position<offers.size()){
            OfferDto offer = offers.get(position);
            holder.offerPrice.setText(offer.price.toString());
            holder.offerGym.setText("Gym: " + offer.price);
            holder.offerDescription.setText(offer.description);
        }
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }
}
