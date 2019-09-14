package com.gymity.admin;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.DatePicker;
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
import com.gymity.model.OfferDto;
import com.gymity.repository.OfferClient;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminOffersFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private MaterialButton addOfferButton;

    private TextInputEditText offerNameEditText;
    private TextInputEditText offerDescriptionEditText;
    private TextInputEditText offerGymEditText;
    private TextInputEditText offerPriceEditText;
    private TextInputEditText offerEndEditText;
    private TextInputEditText offerDurationEditText;

    private TextInputLayout offerNameInputText;
    private TextInputLayout offerDescriptionInputText;
    private TextInputLayout offerGymInputText;
    private TextInputLayout offerPriceInputText;
    private TextInputLayout offerDurationInputText;
    private TextInputLayout offerEndInputText;

    private int yearOfOffer;
    private int monthOfOffer;
    private int dayOfOffer;
    private OfferClient offerClient;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.shr_admin_offers, container, false);
        setUpToolbar(view);

        addOfferButton = view.findViewById(R.id.add_offer_button);
        setUp(view);

        offerEndEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker =
                        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(final DatePicker view, final int year, final int month,
                                                  final int dayOfMonth) {

                                @SuppressLint("SimpleDateFormat")
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                calendar.set(year, month, dayOfMonth);
                                dayOfOffer = dayOfMonth;
                                monthOfOffer = month;
                                yearOfOffer = year;
                                String dateString = sdf.format(calendar.getTime());

                                offerEndEditText.setText(dateString); // set the date
                            }
                        }, year, month, day); // set date picker to current date

                datePicker.show();

                datePicker.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(final DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
            }
        });

        addOfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!formIsValid(offerDescriptionEditText.getText(), offerDurationEditText.getText(), offerEndEditText.getText(), offerGymEditText.getText(), offerNameEditText.getText(), offerPriceEditText.getText()))
                    setErrorFields();
                else {
                    clearErrorFields();
                    offerClient = GymApiClient.getRetrofitInstance().create(OfferClient.class);
                    Call<OfferDto> call = offerClient.addOffer(new OfferDto(new Gym(offerGymEditText.getText().toString(), ""), offerNameEditText.getText().toString(), offerDescriptionEditText.getText().toString(), Long.parseLong(offerDurationEditText.getText().toString()), Long.parseLong(offerPriceEditText.getText().toString()), yearOfOffer, monthOfOffer, dayOfOffer));
                    call.enqueue(new Callback<OfferDto>() {
                        @Override
                        public void onResponse(Call<OfferDto> call, Response<OfferDto> response) {
                            if (response.code() >= 200 && response.code() < 300) {
                                ((NavigationHost) getActivity()).navigateTo(new AdminProductGridFragment(), false);
                            } else {
                                Toast.makeText(getContext(), "Gym doesn't exist", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<OfferDto> call, Throwable t) {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
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
        offerNameEditText = view.findViewById(R.id.offer_name_edit_text);
        offerDescriptionEditText = view.findViewById(R.id.offer_description_edit_text);
        offerPriceEditText = view.findViewById(R.id.offer_price_edit_text);
        offerGymEditText = view.findViewById(R.id.offer_gym_edit_text);
        offerDurationEditText = view.findViewById(R.id.offer_duration_edit_text);
        offerEndEditText = view.findViewById(R.id.offer_end_edit_text);
        offerEndEditText.setClickable(true);

        offerDescriptionInputText = view.findViewById(R.id.offer_description_text_input);
        offerNameInputText = view.findViewById(R.id.offer_name_text_input);
        offerPriceInputText = view.findViewById(R.id.offer_price_text_input);
        offerGymInputText = view.findViewById(R.id.offer_gym_text_input);
        offerEndInputText = view.findViewById(R.id.offer_end_text_input);
        offerDurationInputText = view.findViewById(R.id.offer_duration_text_input);
    }

    public boolean formIsValid(@Nullable Editable description, @Nullable Editable duration, @Nullable Editable end, @Nullable Editable gym, @Nullable Editable name, @Nullable Editable price) {
        return isFieldValid(description) && isFieldValid(duration) && isFieldValid(price) && isFieldValid(name) && isFieldValid(gym);
    }

    public boolean isFieldValid(@Nullable Editable field) {
        return field.length() > 0;
    }

    public void setErrorFields() {
        if (!isFieldValid(offerNameEditText.getText()))
            offerNameInputText.setError("Name of offer can't be empty");
        if (!isFieldValid(offerDescriptionEditText.getText()))
            offerDescriptionInputText.setError("Description of offer can't be empty");
        if (!isFieldValid(offerDurationEditText.getText()))
            offerDurationInputText.setError("Duration of offer can't be empty");
        if (!isFieldValid(offerPriceEditText.getText()))
            offerPriceInputText.setError("Price of offer can't be empty");
        if (!isFieldValid(offerGymEditText.getText()))
            offerGymInputText.setError("Name of gym can't be empty");
    }

    public void clearErrorFields() {
        offerGymInputText.setError(null);
        offerPriceInputText.setError(null);
        offerDurationInputText.setError(null);
        offerDescriptionInputText.setError(null);
        offerNameInputText.setError(null);
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                yearOfOffer = year;
                monthOfOffer = month;
                dayOfOffer = dayOfMonth;
                updateLabel();
            }
        }
    };

    public void updateLabel() {
        String format = "dd/MM/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            offerEndEditText.setText(dateFormat.format(LocalDate.of(yearOfOffer, monthOfOffer, dayOfOffer)));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.shr_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }
}
