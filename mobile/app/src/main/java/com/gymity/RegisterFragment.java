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

public class RegisterFragment extends Fragment {

    private TextInputEditText passwordEditText;
    private MaterialButton nextButton;
    private TextInputEditText usernameEditText;
    private TextInputLayout passwordTextInput;
    private TextInputLayout usernameTextInput;
    private TextInputEditText fullNameEditText;
    private TextInputLayout fullNameTextInput;
    private MaterialButton registerButton;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shr_register, container, false);
//        setUpToolbar(view);

        usernameEditText = view.findViewById(R.id.username_edit_text);
        usernameTextInput = view.findViewById(R.id.username_text_input);
        passwordTextInput = view.findViewById(R.id.password_text_input);
        passwordEditText = view.findViewById(R.id.password_edit_text);
        fullNameEditText = view.findViewById(R.id.fullName_edit_text);
        fullNameTextInput = view.findViewById(R.id.fullName_text_input);
        nextButton = view.findViewById(R.id.next_button);
        registerButton = view.findViewById(R.id.register_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!formIsValid(fullNameEditText.getText(), usernameEditText.getText(), passwordEditText.getText())) {
                    fillErrorFields(fullNameEditText.getText(), usernameEditText.getText(), passwordEditText.getText());
                } else {
                    clearErrorFields();
                    ((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false);
                }
            }
        });

        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (isPasswordValid(passwordEditText.getText()))
                    passwordTextInput.setError(null);
                else
                    passwordTextInput.setError("Password must be longer than 6 characters");
                return false;
            }
        });


        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_register_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
    }

    public boolean formIsValid(@Nullable Editable fullName, @Nullable Editable username, @Nullable Editable password) {
        return isFieldValid(fullName) && isFieldValid(username) && isPasswordValid(password);
    }

    public boolean isPasswordValid(@Nullable Editable password) {
        return password.length() > 5;
    }

    public boolean isFieldValid(@Nullable Editable field) {
        return field.length() > 0;
    }

    public void fillErrorFields(@Nullable Editable fullName, @Nullable Editable username, @Nullable Editable password) {
        clearErrorFields();

        if (!isPasswordValid(password))
            passwordTextInput.setError("Password must be longer than 6 characters");
        if (!isFieldValid(username))
            usernameTextInput.setError("Username can't be empty");
        if (!isFieldValid(fullName))
            fullNameTextInput.setError("Full name can't be empty");

    }

    public void clearErrorFields() {
        passwordTextInput.setError(null);
        usernameTextInput.setError(null);
        fullNameTextInput.setError(null);
    }
}

