package com.gymity;

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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.gymity.adapters.CommentCardRecyclerViewAdapter;
import com.gymity.adapters.OfferCardRecyclerViewAdapter;
import com.gymity.clients.GymApiClient;
import com.gymity.model.Comment;
import com.gymity.model.OfferDto;
import com.gymity.repository.GymClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GymInfoFragment extends Fragment {

    private List<OfferDto> gymOffers;
    private List<Comment> gymComments;
    private GymClient gymClient;
    private MaterialButton gymButton;
    private MaterialButton offersButton;
    private MaterialButton notificationsButton;
    private MaterialButton myAccountButton;
    private MaterialButton logoutButton;

    Long gymId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.shr_gym_info, container, false);
        setUpToolbar(view);
        setUpView(view);

        gymId = SaveSharedPreference.getGymId(getActivity());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.findViewById(R.id.product_grid).setBackgroundResource(R.drawable.shr_product_grid_background_shape);
        }

        makeApiCallForGettingGymOffers(view);
        makeApiCallForGettingGymComments(view);

        gymButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new GymsFragment(), true);
            }
        });

        offersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new OffersFragment(), true);
            }
        });

        notificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new NotificationsFragment(), true);
            }
        });

        myAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new MyAccountFragment(), true);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveSharedPreference.clearUsernameOnLogout(getActivity());
                ((NavigationHost) getActivity()).navigateTo(new LoginFragment(), true);
            }
        });

        return view;
    }

    private void makeApiCallForGettingGymOffers(final View view) {
        gymClient = GymApiClient.getRetrofitInstance().create(GymClient.class);
        Call<List<OfferDto>> call = gymClient.getGymOffers(gymId);
        call.enqueue(new Callback<List<OfferDto>>() {
            @Override
            public void onResponse(Call<List<OfferDto>> call, Response<List<OfferDto>> response) {
                if (response.code() >= 200 && response.code() < 300) {
                    gymOffers = response.body();
                    setUpRecyclerViewOffers(view);
                } else
                    Toast.makeText(getContext(), "No offers available", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<List<OfferDto>> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT);
            }
        });
    }

    private void makeApiCallForGettingGymComments(final View view) {
        gymClient = GymApiClient.getRetrofitInstance().create(GymClient.class);
        Call<List<Comment>> call = gymClient.getGymComments(gymId);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.code() >= 200 && response.code() < 300) {
                    gymComments = response.body();
                    setUpRecyclerViewComments(view);
                } else
                    Toast.makeText(getContext(), "No comments available", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT);
            }
        });
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
                getContext().getResources().getDrawable(R.drawable.shr_menu), // Menu open icon
                getContext().getResources().getDrawable(R.drawable.shr_close_menu))); // Menu close icon
    }

    private void setUpView(View view) {
        gymButton = view.findViewById(R.id.gyms_button);
        offersButton = view.findViewById(R.id.offers_button);
        notificationsButton = view.findViewById(R.id.notifications_button);
        myAccountButton = view.findViewById(R.id.my_account_button);
        logoutButton = view.findViewById(R.id.logout_button);

    }

    public void setUpRecyclerViewOffers(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_offers);
        setUpRecyclerViewHorizontal(recyclerView);

        OfferCardRecyclerViewAdapter adapter = new OfferCardRecyclerViewAdapter(gymOffers);
        recyclerView.setAdapter(adapter);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(smallPadding, smallPadding));
    }

    public void setUpRecyclerViewComments(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_comments);
        setUpRecyclerViewVertical(recyclerView);

        CommentCardRecyclerViewAdapter adapter = new CommentCardRecyclerViewAdapter(gymComments);
        recyclerView.setAdapter(adapter);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(smallPadding, smallPadding));
    }

    public void setUpRecyclerViewHorizontal(RecyclerView recyclerView) {
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

    public void setUpRecyclerViewVertical(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.shr_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }
}
