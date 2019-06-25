package com.gymity.admin;

import android.os.Bundle;
import android.text.Editable;
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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.gymity.NavigationHost;
import com.gymity.NavigationIconClickListener;
import com.gymity.R;
import com.gymity.clients.GymApiClient;
import com.gymity.model.Gym;
import com.gymity.repository.GymClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminGymsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private MaterialButton addGymButton;

    private TextInputEditText gymNameEditText;
    private TextInputEditText gymLocationEditText;

    private TextInputLayout gymNameInput;
    private TextInputLayout gymLocationInput;

    private GymClient gymClient;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.shr_admin_gyms, container, false);
        setUpToolbar(view);

        setUp(view);

        addGymButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!formIsValid(gymNameEditText.getText(), gymLocationEditText.getText()))
                    setErrorFields();
                else {
                    clearErrorFields();
                    gymClient = GymApiClient.getRetrofitInstance().create(GymClient.class);
                    Call<Gym> call = gymClient.addGym(new Gym(gymNameEditText.getText().toString(), gymLocationEditText.getText().toString()));
                    call.enqueue(new Callback<Gym>() {
                        @Override
                        public void onResponse(Call<Gym> call, Response<Gym> response) {
                            if (response.code() >= 200 && response.code() < 300) {
                                ((NavigationHost) getActivity()).navigateTo(new AdminProductGridFragment(), false);
                            } else {
                                Toast.makeText(getContext(), "Gym already exists", Toast.LENGTH_SHORT);
                            }
                        }

                        @Override
                        public void onFailure(Call<Gym> call, Throwable t) {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT);
                        }
                    });
                }
            }
        });

        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_admin_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                getContext(),
                view.findViewById(R.id.bottom_bar),
                new AccelerateDecelerateInterpolator(),
                getContext().getResources().getDrawable(R.drawable.shr_menu),
                getContext().getResources().getDrawable(R.drawable.shr_close_menu)));
    }

    public void setUp(View view) {
        addGymButton = view.findViewById(R.id.add_gym_button);

        gymLocationEditText = view.findViewById(R.id.location_edit_text);
        gymNameEditText = view.findViewById(R.id.gym_name_edit_text);

        gymLocationInput = view.findViewById(R.id.location_text_input);
        gymNameInput = view.findViewById(R.id.gym_name_text_input);
    }

    public boolean formIsValid(@Nullable Editable gymName, @Nullable Editable location) {
        return isFieldValid(gymName) && isFieldValid(location);
    }

    public boolean isFieldValid(@Nullable Editable field) {
        return field.length() > 0;
    }

    public void setErrorFields() {
        if (!isFieldValid(gymNameEditText.getText()))
            gymNameInput.setError("Name of gym can't be empty");
        if (!isFieldValid(gymLocationEditText.getText()))
            gymLocationInput.setError("Location of gym can't be empty");
    }

    public void clearErrorFields() {
        gymNameInput.setError(null);
        gymLocationInput.setError(null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.shr_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }
}
