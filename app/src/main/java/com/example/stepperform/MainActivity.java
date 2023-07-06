package com.example.stepperform;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ImageButton step1Button;
    private ImageButton step2Button;
    private ImageButton step3Button;
    private View contactLeft;
    private View contactRight;
    private View OtherRight;
    private TextView stepTwoTextView;
    private TextView stepThreeTextView;
    private ProgressBar progressBar;
    private ImageButton previousButton;
    private ImageButton nextButton;
    private FrameLayout stepContentContainer;
    private View stepOneLayout;
    private View stepTwoLayout;
    private View stepThreeLayout;
    private int currentStep = 1; // track step for next and previous button

    // Declare Form part one field
    private TextInputEditText firstName;
    private TextInputEditText middleName;
    private TextInputEditText lastName;
    private TextInputLayout DOB_layout;
    private TextInputEditText DOB;
    private TextInputEditText gender;

    // Declare Form part two field
    private TextInputEditText email;
    private TextInputEditText phoneNumber;
    private TextInputEditText streetName;
    private TextInputEditText city;
    private TextInputEditText state;
    private TextInputEditText zipCode;

    // Declare Form part two field
    private ImageView profileImage;
    private TextInputEditText position;
    private TextInputEditText password;
    private TextInputEditText confirmedPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_horizontal);

        step1Button = findViewById(R.id.stepOneButton);
        step2Button = findViewById(R.id.stepTwoButton);
        step3Button = findViewById(R.id.stepThreeButton);

        contactLeft = findViewById(R.id.contactLeftView);
        contactRight = findViewById(R.id.contactRightView);
        OtherRight = findViewById(R.id.OtherRightView);

        stepTwoTextView = findViewById(R.id.stepTwoTextView);
        stepThreeTextView = findViewById(R.id.stepThreeTextView);

        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);

        stepContentContainer = findViewById(R.id.stepContentContainer);

        // Steps layout
        stepOneLayout = LayoutInflater.from(this).inflate(R.layout.form_step_one, null);
        stepTwoLayout = LayoutInflater.from(this).inflate(R.layout.form_step_two, null);
        stepThreeLayout = LayoutInflater.from(this).inflate(R.layout.form_step_three, null);

        // Step one form field
        firstName = stepOneLayout.findViewById(R.id.signupFirstNameText);
        middleName = stepOneLayout.findViewById(R.id.signupMiddleNameText);
        lastName = stepOneLayout.findViewById(R.id.signupLastNameText);
        DOB_layout = stepOneLayout.findViewById(R.id.signupDOBLayout);
        DOB = stepOneLayout.findViewById(R.id.signupDOBText);
        gender = stepOneLayout.findViewById(R.id.signupGenderText);

        // Step two form field
        email = stepTwoLayout.findViewById(R.id.signupEmailText);
        phoneNumber = stepTwoLayout.findViewById(R.id.signupPhoneNumberText);;
        streetName = stepTwoLayout.findViewById(R.id.signupStreetNameText);
        city = stepTwoLayout.findViewById(R.id.signupCityText);
        state = stepTwoLayout.findViewById(R.id.signupStateText);
        zipCode = stepTwoLayout.findViewById(R.id.signupZipCodeText);

        // Step three form field
        profileImage = stepThreeLayout.findViewById(R.id.newProfileImage);
        position = stepThreeLayout.findViewById(R.id.signupPositionText);
        password = stepThreeLayout.findViewById(R.id.signupPasswordText);
        confirmedPassword = stepThreeLayout.findViewById(R.id.signupConfirmPasswordText);


        // When user click Calendar icon
        DOB_layout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate();
            }
        });


        // Progress bar default value
        progressBar.setProgress(33);

        step1Button.setOnClickListener(v -> {
            // Handle Step 1 button click
            showStepContent(1);
        });

        step2Button.setOnClickListener(v -> {
            // Handle Step 2 button click
            showStepContent(2);
        });

        step3Button.setOnClickListener(v -> {
            // Handle Step 3 button click
            showStepContent(3);
        });

        previousButton.setOnClickListener(v -> {
            currentStep--; // Decrease currentStep by 1
            showStepContent(currentStep);
        });

        nextButton.setOnClickListener(v -> {
            currentStep++; // Increase currentStep by 1
            showStepContent(currentStep);
        });

        // Show the initial step content
        showStepContent(currentStep);
    }

    private void showStepContent(int step) {
        // Default Color
        int colorBlue = ContextCompat.getColor(this, R.color.blue);
        int colorGray = ContextCompat.getColor(this, R.color.light_gray);
        int colorWhite = ContextCompat.getColor(this, R.color.white);

        if (step < 1) {
            step = 1;
        }

//        else if(step > 3) {
//            step = 3;
//        }

        // Set active button background
        switch (step) {
            case 1:
                stepContentContainer.removeAllViews();
                stepContentContainer.addView(stepOneLayout);

                currentStep = 1; // Reset currentStep value to one

                step1Button.setClickable(false); // Make step1Button not clickable
                step2Button.setClickable(true); // Make step2Button clickable
                step3Button.setClickable(true); // Make step3Button clickable

                previousButton.setClickable(false); // Make previousButton not clickable
                nextButton.setClickable(true); // Make nextButton clickable

                // Reset other views colors to gray
                contactLeft.setBackgroundColor(colorGray);
                step2Button.setBackgroundTintList(ColorStateList.valueOf(colorGray));
                stepTwoTextView.setTextColor(colorGray);
                contactRight.setBackgroundColor(colorGray);
                step3Button.setBackgroundTintList(ColorStateList.valueOf(colorGray));
                stepThreeTextView.setTextColor(colorGray);
                OtherRight.setBackgroundColor(colorGray);

                progressBar.setProgress(33);

                // Reset previous button color
                previousButton.setBackgroundResource(R.drawable.button_style);
                previousButton.setColorFilter(colorBlue);

                // Reset next button color
                nextButton.setImageResource(R.drawable.baseline_arrow_forward_24);
                nextButton.setBackgroundColor(colorBlue);
                nextButton.setColorFilter(colorWhite);

                break;
            case 2:
                stepContentContainer.removeAllViews();
                stepContentContainer.addView(stepTwoLayout);

                currentStep = 2; // Reset currentStep value to two

                // Step button clickable
                step1Button.setClickable(true);
                step2Button.setClickable(false);
                step3Button.setClickable(true);

                previousButton.setClickable(true); // Make previousButton to clickable
                nextButton.setClickable(true); // Make nextButton to clickable

                contactLeft.setBackgroundColor(colorBlue);
                step2Button.setBackgroundTintList(ColorStateList.valueOf(colorBlue));
                stepTwoTextView.setTextColor(colorBlue);

                // Reset other views colors to gray
                contactRight.setBackgroundColor(colorGray);
                step3Button.setBackgroundTintList(ColorStateList.valueOf(colorGray));
                stepThreeTextView.setTextColor(colorGray);
                OtherRight.setBackgroundColor(colorGray);

                progressBar.setProgress(58);

                // Change previous button color
                previousButton.setBackgroundColor(colorBlue);
                previousButton.setColorFilter(colorWhite);

                // Reset next button color
                nextButton.setImageResource(R.drawable.baseline_arrow_forward_24); // reset button icon
                nextButton.setBackgroundColor(colorBlue);
                nextButton.setColorFilter(colorWhite);

                break;
            case 3:
                stepContentContainer.removeAllViews();
                stepContentContainer.addView(stepThreeLayout);

                currentStep = 3; // Reset currentStep value to three

                // Step button clickable
                step1Button.setClickable(true);
                step2Button.setClickable(true);
                step3Button.setClickable(false);

                previousButton.setClickable(true); // Make previousButton to clickable
               // nextButton.setClickable(false);  // Make nextButton to clickable
                nextButton.setClickable(true);

                contactLeft.setBackgroundColor(colorBlue);
                step2Button.setBackgroundTintList(ColorStateList.valueOf(colorBlue));
                stepTwoTextView.setTextColor(colorBlue);
                contactRight.setBackgroundColor(colorBlue);
                step3Button.setBackgroundTintList(ColorStateList.valueOf(colorBlue));
                stepThreeTextView.setTextColor(colorBlue);
                OtherRight.setBackgroundColor(colorBlue);

                progressBar.setProgress(100);

                // Change previous button color
                previousButton.setBackgroundColor(colorBlue);
                previousButton.setColorFilter(colorWhite);

                // Change next button color
                nextButton.setImageResource(R.drawable.baseline_check_24); // Replace button icon
                nextButton.setBackgroundResource(R.drawable.button_style);
                nextButton.setColorFilter(colorBlue);

                break;

            case 4:
//                Toast.makeText(this, firstName.getText(), Toast.LENGTH_SHORT).show();
                showDialogMessage();

                break;
        }

    }

    private void selectDate() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            // When the user selects a date, format it as a string and set it as the text of the EditText
            calendar.set(year, month, dayOfMonth);

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            String formattedDate = dateFormat.format(calendar.getTime());

            DOB.setText(formattedDate);
        };

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create and show the date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                dateSetListener,
                year,
                month,
                dayOfMonth);
        datePickerDialog.show();
    }

    private void showDialogMessage() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Confirm")
                .setMessage("Do you want to create a new user?")
                .setNegativeButton("No", (dialog, which) -> {
                    // If user click on NO nothing happen
                })
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Open ConfirmationFragment when user click on YES

                }).show();
    }
}
