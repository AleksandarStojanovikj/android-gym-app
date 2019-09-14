package com.gymity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gymity.R;
import com.gymity.model.OfferDto;
import com.gymity.viewholders.MyOfferCardViewHolder;

import java.util.List;

public class MyOfferCardRecyclerViewAdapter extends RecyclerView.Adapter<MyOfferCardViewHolder> {

    private List<OfferDto> offers;
    private int screenWidth = 0;
    private Context context;

    public MyOfferCardRecyclerViewAdapter(List<OfferDto> offers) {
        this.offers = offers;
    }

    @NonNull
    @Override
    public MyOfferCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shr_my_offer_card, parent, false);
        context = parent.getContext();
        return new MyOfferCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOfferCardViewHolder holder, final int position) {
        if (offers != null && position < offers.size()) {
            OfferDto offer = offers.get(position);
            holder.offerPrice.setText(offer.price.toString() + " denars");
            holder.offerGym.setText("Gym: " + offer.gym.name);
            holder.offerDescription.setText(offer.description);

        }
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }
}
