package com.gymity;

import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.gymity.admin.AdminProductGridFragment;
import com.gymity.clients.GymApiClient;
import com.gymity.model.Credentials;
import com.gymity.model.Users;
import com.gymity.persistance.UserRepository;
import com.gymity.repository.UserClient;
import com.gymity.viewmodels.UserViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private UserRepository userRepository;
    private UserViewModel userViewModel;
    private TextInputEditText passwordEditText;
    private MaterialButton nextButton;
    private TextInputEditText usernameEditText;
    private TextInputLayout passwordTextInput;
    private MaterialButton registerButton;
    private UserClient userClient;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shr_login_fragment, container, false);

        usernameEditText = view.findViewById(R.id.username_edit_text);
        passwordTextInput = view.findViewById(R.id.password_text_input);
        passwordEditText = view.findViewById(R.id.password_edit_text);
        nextButton = view.findViewById(R.id.next_button);
        registerButton = view.findViewById(R.id.register_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userClient = GymApiClient.getRetrofitInstance().create(UserClient.class);
                Call<Users> call = userClient.loginUser(new Credentials(usernameEditText.getText().toString(), passwordEditText.getText().toString()));
                call.enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        if (response.code() == 200) {
                            if (response.body().isAdmin) {
                                ((NavigationHost) getActivity()).navigateTo(new AdminProductGridFragment(), false);
                            } else {
                                ((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false);
                            }

                            SaveSharedPreference.setUserOnLogin(getActivity(), response.body().credentials.username, response.body().isAdmin);

                        } else
                            Toast.makeText(getContext(), "Incorrect credentials", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new RegisterFragment(), true);
            }
        });

        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (isPasswordValid(passwordEditText.getText()))
                    passwordTextInput.setError(null);
                return false;
            }
        });

        return view;
    }

    public boolean isPasswordValid(@Nullable Editable password) {
        return password.length() > 5;
    }
}
