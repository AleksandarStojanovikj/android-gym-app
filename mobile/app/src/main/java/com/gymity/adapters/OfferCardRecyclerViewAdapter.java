package com.gymity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gymity.R;
import com.gymity.SaveSharedPreference;
import com.gymity.clients.GymApiClient;
import com.gymity.model.OfferDto;
import com.gymity.repository.UserClient;
import com.gymity.viewholders.OfferCardViewHolder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferCardRecyclerViewAdapter extends RecyclerView.Adapter<OfferCardViewHolder> {

    private List<OfferDto> offers;
    private int screenWidth = 0;
    private Context context;

    public OfferCardRecyclerViewAdapter(List<OfferDto> offers) {
        this.offers = offers;
    }

    @NonNull
    @Override
    public OfferCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shr_offer_card, parent, false);
        context = parent.getContext();
        return new OfferCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferCardViewHolder holder, final int position) {
        if (offers != null && position < offers.size()) {
            OfferDto offer = offers.get(position);
            holder.offerPrice.setText(offer.price.toString() + " denars");
            holder.offerGym.setText("Gym: " + offer.gym.name);
            holder.offerDescription.setText(offer.description);


            holder.subscribeToOffer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makeApiCallForSubscribingToOffer(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    private void makeApiCallForSubscribingToOffer(int position) {
        UserClient userClient = GymApiClient.getRetrofitInstance().create(UserClient.class);
        Call<OfferDto> subscribeToOffer = userClient.subscribeToOffer(SaveSharedPreference.getUsername(context), offers.get(position));

        subscribeToOffer.enqueue(new Callback<OfferDto>() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.code() >= 200 && response.code() < 300) {
                    Toast.makeText(context, "You have successfully subscribed to offer", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 409) {
                    Toast.makeText(context, "You have already subscribed to this offer", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
