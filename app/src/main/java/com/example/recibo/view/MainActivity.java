 package com.example.recibo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recibo.R;
import com.example.recibo.databinding.ActivityMainBinding;
import com.example.recibo.model.UserDetails;
import com.example.recibo.viewModel.UserDetailsViewModel;

 public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     private static final String TAG = "MAIN_ACTIVITY";
     private ActivityMainBinding activityMainBinding;
    private EditText firstNameEditText, lastNameEditText, mobileNoEditText;
    private TextView firstNameTextView, lastNameTextView, mobileNoTextView;
    private Button saveDetailsButton;
    private UserDetailsViewModel mUserDetailsViewModel;
    private UserDetails userDetails;
    private String firstNameString = "First Name";
    private String lastNameString = "Last Name";
    private String mobileNumberString = "Mobile Number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        firstNameEditText = activityMainBinding.firstNameEditText;
        lastNameEditText = activityMainBinding.lastNameEditText;
        mobileNoEditText = activityMainBinding.mobileNoEditText;
        saveDetailsButton = activityMainBinding.saveButton;
        firstNameTextView = activityMainBinding.firstNameTextView;
        lastNameTextView = activityMainBinding.lastNameTextView;
        mobileNoTextView = activityMainBinding.mobileNoTextView;

        mUserDetailsViewModel = new ViewModelProvider(this).get(UserDetailsViewModel.class);

        mUserDetailsViewModel.getUserDetails().observe(this, userDetails -> {

            Log.d(TAG, "Get user details...");

            //Set text view's with user details if not null...
            if(!TextUtils.isEmpty(userDetails.getFirstName())){
                Log.d(TAG, "First name: "+userDetails.getFirstName());
                firstNameTextView.setText(userDetails.getFirstName());
            }else {
                Log.d(TAG, "First name null");
                firstNameTextView.setText(firstNameString);
            }
            if(!TextUtils.isEmpty(userDetails.getLastName())){
                Log.d(TAG, "Last name: "+userDetails.getLastName());
                lastNameTextView.setText(userDetails.getLastName());
            }else {
                Log.d(TAG, "Last name null");
                lastNameTextView.setText(lastNameString);
            }
            if(!TextUtils.isEmpty(userDetails.getMobileNumber())){
                Log.d(TAG, "Mobile number: "+userDetails.getMobileNumber());
                mobileNoTextView.setText(userDetails.getMobileNumber());
            }else {
                Log.d(TAG, "Mobile number null");
                mobileNoTextView.setText(mobileNumberString);
            }

        });

        saveDetailsButton.setOnClickListener(this);
    }

     @Override
     public void onClick(View v) {
         if (v == saveDetailsButton){
             Log.d(TAG, "Add Button Clicked");
             userDetails = new UserDetails();
             String firstName = firstNameEditText.getText().toString().trim();
             String lastName = lastNameEditText.getText().toString().trim();
             String mobileNo = mobileNoEditText.getText().toString().trim();

             Log.d(TAG, "User first name: "+firstName);
             Log.d(TAG, "User last name: "+lastName);
             Log.d(TAG, "User mobile number: "+mobileNo);

             if (!TextUtils.isEmpty(firstName)){
                 userDetails.setFirstName(firstName);
             }
             if(!TextUtils.isEmpty(lastName)){
                 userDetails.setLastName(lastName);
             }
             if (!TextUtils.isEmpty(mobileNo)){
                 userDetails.setMobileNumber(mobileNo);
             }

             // Insert User Details Into Room Database...
            new Thread(() -> {
                Log.d(TAG, "Inserting user details in background...");
                mUserDetailsViewModel.insert(userDetails);
            });

         }
     }
 }