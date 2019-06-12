package com.gymity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class RegisterFragment extends Fragment {

    private MaterialButton registerButton;

    private TextInputEditText confirmPasswordInput;
    private TextInputEditText passwordInput;
    private TextInputEditText usernameInput;
    private TextInputEditText fullNameInput;

    private TextInputLayout usernamerLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout fullNameLayout;
    private TextInputLayout confirmPasswordLayout;

    Map<String, String> errors = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.shr_register, container, false);

        registerButton = view.findViewById(R.id.register_button);

        confirmPasswordInput = view.findViewById(R.id.register_confirm_password);
        passwordInput = view.findViewById(R.id.register_password);
        usernameInput = view.findViewById(R.id.register_username);
        fullNameInput = view.findViewById(R.id.register_full_name);

        usernamerLayout = view.findViewById(R.id.username_text_input);
        passwordLayout = view.findViewById(R.id.password_text_input);
        confirmPasswordLayout = view.findViewById(R.id.confirm_password_text_input);
        fullNameLayout = view.findViewById(R.id.full_name_text_input);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formIsValid()){
                    ((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false);
                }
            }
        });

        setUpToolbar(view);
        return view;
    }

    public boolean formIsValid(){
        if(this.usernameInput.getText().length() == 0){
            errors.put("username", "Username can't be empty");
        }
        return true;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
    }

}
