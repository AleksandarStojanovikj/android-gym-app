package com.gymity.admin;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.gymity.NavigationHost;
import com.gymity.NavigationIconClickListener;
import com.gymity.ProductGridItemDecoration;
import com.gymity.R;
import com.gymity.adapters.GymCardRecyclerViewAdapter;
import com.gymity.adapters.OfferCardRecyclerViewAdapter;
import com.gymity.clients.GymApiClient;
import com.gymity.model.Gym;
import com.gymity.model.OfferDto;
import com.gymity.model.Users;
import com.gymity.repository.GymClient;
import com.gymity.repository.OfferClient;
import com.gymity.repository.UserClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminProductGridFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private MaterialButton gymButton;
    private MaterialButton offersButton;
    private MaterialButton notificationsButton;
    private MaterialButton usersButton;

    private OfferClient offerClient;
    private List<OfferDto> offers;
    private List<Gym> gyms;
    private GymClient gymClient;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.shr_admin_product_grid, container, false);
        setUpToolbar(view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.findViewById(R.id.product_grid).setBackgroundResource(R.drawable.shr_product_grid_background_shape);
        }

        offerClient = GymApiClient.getRetrofitInstance().create(OfferClient.class);
        Call<List<OfferDto>> call = offerClient.getOffers();
        call.enqueue(new Callback<List<OfferDto>>() {
            @Override
            public void onResponse(Call<List<OfferDto>> call, Response<List<OfferDto>> response) {
                if (response.code() >= 200 && response.code() < 300) {
                    offers = response.body();
                    setUpRecyclerViewOffers(view);
                } else
                    Toast.makeText(getContext(), "No offers available", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<List<OfferDto>> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT);
            }
        });

        gymClient = GymApiClient.getRetrofitInstance().create(GymClient.class);
        Call<List<Gym>> gymCall = gymClient.getGyms();
        gymCall.enqueue(new Callback<List<Gym>>() {
            @Override
            public void onResponse(Call<List<Gym>> call, Response<List<Gym>> response) {
                if (response.code() >= 200 && response.code() < 300) {
                    gyms = response.body();
                    setUpRecyclerViewGyms(view);
                } else {
                    Toast.makeText(getContext(), "No gyms available", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<List<Gym>> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT);
            }

        });


        gymButton = view.findViewById(R.id.gyms_button);
        offersButton = view.findViewById(R.id.offers_button);
        notificationsButton = view.findViewById(R.id.notifications_button);
        usersButton = view.findViewById(R.id.users_button);


        gymButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new AdminGymsFragment(), true);
            }
        });

        offersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new AdminOffersFragment(), true);
            }
        });

        notificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new AdminNotificationsFragment(), true);
            }
        });

        usersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new AdminUsersFragment(), true);
            }
        });
        return view;
    }

    public void setUpRecyclerViewOffers(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_offers);
        setUpRecyclerView(recyclerView);

        OfferCardRecyclerViewAdapter adapter = new OfferCardRecyclerViewAdapter(offers);
        recyclerView.setAdapter(adapter);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(smallPadding, smallPadding));
    }

    public void setUpRecyclerViewGyms(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_gyms);
        setUpRecyclerView(recyclerView);

        GymCardRecyclerViewAdapter adapter = new GymCardRecyclerViewAdapter(gyms);
        recyclerView.setAdapter(adapter);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(smallPadding, smallPadding));
    }


    public void setUpRecyclerView(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                getContext(),
                view.findViewById(R.id.product_grid),
                new AccelerateDecelerateInterpolator(),
                getContext().getResources().getDrawable(R.drawable.shr_menu),
                getContext().getResources().getDrawable(R.drawable.shr_close_menu)));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.shr_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }
}
