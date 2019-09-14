package com.gymity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gymity.MyAccountFragment;
import com.gymity.NavigationHost;
import com.gymity.R;
import com.gymity.SaveSharedPreference;
import com.gymity.clients.GymApiClient;
import com.gymity.model.Gym;
import com.gymity.repository.UserClient;
import com.gymity.viewholders.MyGymCardViewHolder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyGymCardRecyclerViewAdapter extends RecyclerView.Adapter<MyGymCardViewHolder> {

    private List<Gym> gyms;
    private Context context;

    public MyGymCardRecyclerViewAdapter(List<Gym> gyms) {
        this.gyms = gyms;
    }

    @NonNull
    @Override
    public MyGymCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shr_my_gym_card, parent, false);
        context = parent.getContext();
        return new MyGymCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyGymCardViewHolder holder, final int position) {
        if (gyms != null && position < gyms.size()) {
            Gym gym = gyms.get(position);
            holder.gymName.setText(gym.name);
            holder.gymLocation.setText("Located: " + gym.location);


            holder.unsubscribeFromGym.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makeApiCallForUnsubscribingFromGym(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return gyms.size();
    }

    private void makeApiCallForUnsubscribingFromGym(int position) {
        UserClient userClient = GymApiClient.getRetrofitInstance().create(UserClient.class);
        Call<String> unsubscribeFromGym = userClient.unsubscribeFromGym(SaveSharedPreference.getUsername(context), gyms.get(position).name);

        unsubscribeFromGym.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.code() >= 200 && response.code() < 300) {
                    Toast.makeText(context, "You have successfully unsubscribed to gym", Toast.LENGTH_SHORT).show();
                    ((NavigationHost) context).navigateTo(new MyAccountFragment(), true);
                } else if (response.code() == 404) {
                    Toast.makeText(context, "You were not subscribed to this gym", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, "Something went wrong! Please try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }
}