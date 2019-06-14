package com.gymity;

import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
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

    private TextInputEditText passwordInput;
    private TextInputEditText usernameInput;
    private TextInputEditText fullNameInput;

    private TextInputLayout usernameLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout fullNameLayout;

    Map<String, String> errors = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.shr_register, container, false);

        registerButton = view.findViewById(R.id.register_button);

        passwordInput = view.findViewById(R.id.register_password);
        usernameInput = view.findViewById(R.id.register_username);
        fullNameInput = view.findViewById(R.id.register_full_name);

        usernameLayout = view.findViewById(R.id.username_text_input);
        passwordLayout = view.findViewById(R.id.password_text_input);
        fullNameLayout = view.findViewById(R.id.full_name_text_input);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!formIsValid()) {
                    fillErrorFields();
                } else {
                    ((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false);
                }
            }
        });

        fullNameInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(isFieldValid(fullNameInput.getText())) {
                    errors.remove("fullName");
                    fullNameLayout.setError(null);
                }
                return false;
            }
        });

        usernameInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(isFieldValid(usernameInput.getText())) {
                    errors.remove("username");
                    usernameLayout.setError(null);
                }
                return false;
            }
        });

        passwordInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(isFieldValid(passwordInput.getText())){
                    errors.remove("password");
                    passwordLayout.setError(null);
                }
                return false;
            }
        });

        setUpToolbar(view);
        return view;
    }

    public boolean formIsValid() {
        if (usernameInput.getText().length() == 0)
            errors.put("username", "Username can't be empty");

        if (passwordInput.getText().length() == 0)
            errors.put("password", "Password can't be empty");

        if(fullNameInput.getText().length() == 0)
            errors.put("fullName", "Full name can't be empty");

        return errors.isEmpty();
    }

    public void fillErrorFields(){
        if(errors.containsKey("username"))
            usernameLayout.setError(errors.get("username"));
        if(errors.containsKey("password"))
            passwordLayout.setError(errors.get("password"));
        if(errors.containsKey("fullName"))
            fullNameLayout.setError(errors.get("fullName"));
    }

    public boolean isFieldValid(@Nullable Editable field) {
        return field.length() > 0;
    }


    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
    }


}
