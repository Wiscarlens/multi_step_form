package com.example.stepperform;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
    ImageButton previousButton;
    ImageButton nextButton;

    private FrameLayout stepContentContainer;
    private int currentStep = 1;

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

        // Progress bar default value
        progressBar.setProgress(33);

        step1Button.setOnClickListener(v -> {
            // Handle Step 1 button click
            showStepContent(1);
            // Reset current step value in case use use step button instead
            currentStep = 1;
        });

        step2Button.setOnClickListener(v -> {
            // Handle Step 2 button click
            showStepContent(2);
            // Reset current step value in case use use step button instead
            currentStep = 2;
        });

        step3Button.setOnClickListener(v -> {
            // Handle Step 3 button click
            showStepContent(3);
            // Reset current step value in case use use step button instead
            currentStep = 3;
        });

        previousButton.setOnClickListener(v -> {
            // Decrease currentStep by 1
            if (currentStep > 1) {
                currentStep--;
                showStepContent(currentStep);
            }
        });

        nextButton.setOnClickListener(v -> {
            // Increase currentStep by 1
            if (currentStep < 3) {
                currentStep++;
                showStepContent(currentStep);
            }
        });

        // Show the initial step content
        showStepContent(1);
    }

    private void showStepContent(int step) {
        View stepOneLayout;
        View stepTwoLayout;
        View stepThreeLayout;

        // Default Color
        int colorBlue = ContextCompat.getColor(this, R.color.blue);
        int colorGray = ContextCompat.getColor(this, R.color.light_gray);
        int colorWhite = ContextCompat.getColor(this, R.color.white);

        // Set active button background
        switch (step) {
            case 1:
                stepOneLayout = LayoutInflater.from(this).inflate(R.layout.form_step_one, null);
                stepContentContainer.removeAllViews();
                stepContentContainer.addView(stepOneLayout);

                previousButton.setClickable(false); // Make previousButton ot clickable
                nextButton.setClickable(true); // Make nextButton to clickable

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
                nextButton.setBackgroundColor(colorBlue);
                nextButton.setColorFilter(colorWhite);

                break;
            case 2:
                stepTwoLayout = LayoutInflater.from(this).inflate(R.layout.form_step_two, null);
                stepContentContainer.removeAllViews();
                stepContentContainer.addView(stepTwoLayout);

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
                nextButton.setBackgroundColor(colorBlue);
                nextButton.setColorFilter(colorWhite);

                break;
            case 3:
                stepThreeLayout = LayoutInflater.from(this).inflate(R.layout.form_step_three, null);
                stepContentContainer.removeAllViews();
                stepContentContainer.addView(stepThreeLayout);

                previousButton.setClickable(true); // Make previousButton to clickable
                nextButton.setClickable(false);  // Make nextButton to clickable

                contactLeft.setBackgroundColor(colorBlue);
                step2Button.setBackgroundTintList(ColorStateList.valueOf(colorBlue));
                stepTwoTextView.setTextColor(colorBlue);
                contactRight.setBackgroundColor(colorBlue);
                step3Button.setBackgroundTintList(ColorStateList.valueOf(colorBlue));
                stepThreeTextView.setTextColor(colorBlue);
                OtherRight.setBackgroundColor(colorBlue);

                progressBar.setProgress(100);

                // Change next button color
                nextButton.setBackgroundResource(R.drawable.button_style);
                nextButton.setColorFilter(colorBlue);

                break;
        }
    }
}
