 package com.example.recibo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;
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
     private String userFirstName, userLastName, userMobileNumber;
     private UserDetails userDetails = new UserDetails();
     private String firstNameString = "User First Name";
     private String lastNameString = "User Last Name";
     private String mobileNumberString = "User Mobile Number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setLifecycleOwner(this);
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

            try {
                userFirstName = userDetails.getFirstName();
                Log.d(TAG, "onCreate: "+userFirstName);
            }catch (Exception e){
                Log.d(TAG, "User Details Exception: "+e.toString());
            }
            if (userDetails != null){
                Log.d(TAG, "Get User Details Not Null...");
                userFirstName = userDetails.getFirstName();
                userLastName = userDetails.getLastName();
                userMobileNumber = userDetails.getMobileNumber();
                Log.d(TAG, "Get First name: "+userFirstName);
                Log.d(TAG, "Get Last name: "+userLastName);
                Log.d(TAG, "Get Mobile number: "+userMobileNumber);


                //Set text view's with user details...
                if(!TextUtils.isEmpty(userFirstName)){
                    firstNameTextView.setText(userFirstName);
                    userDetails.setFirstName(userFirstName);
                }else {
                    Log.d(TAG, "First name null");
                    firstNameTextView.setText(firstNameString);
                }
                if(!TextUtils.isEmpty(userLastName)){
                    lastNameTextView.setText(userLastName);
                    userDetails.setLastName(userLastName);
                }else {
                    Log.d(TAG, "Last name null");
                    lastNameTextView.setText(lastNameString);
                }
                if(!TextUtils.isEmpty(userMobileNumber)){
                    mobileNoTextView.setText(userMobileNumber);
                    userDetails.setMobileNumber(userMobileNumber);
                }else {
                    Log.d(TAG, "Mobile number null");
                    mobileNoTextView.setText(mobileNumberString);
                }
            }else {
                Log.d(TAG, "Get User Details Null...");
            }
        });

        saveDetailsButton.setOnClickListener(this);
    }

     @Override
     public void onClick(View v) {
         if (v == saveDetailsButton){
             Log.d(TAG, "Save Button Clicked");
             String firstName = firstNameEditText.getText().toString().trim();
             String lastName = lastNameEditText.getText().toString().trim();
             String mobileNo = mobileNoEditText.getText().toString().trim();

             Log.d(TAG, "User first name: "+firstName);
             Log.d(TAG, "User last name: "+lastName);
             Log.d(TAG, "User mobile number: "+mobileNo);

             if (!TextUtils.isEmpty(firstName)){
                 userDetails.setFirstName(firstName);
                 firstNameEditText.getText().clear();
             }
             if (!TextUtils.isEmpty(lastName)){
                 userDetails.setLastName(lastName);
                 lastNameEditText.getText().clear();
             }
             if (!TextUtils.isEmpty(mobileNo)){
                 userDetails.setMobileNumber(mobileNo);
                 mobileNoEditText.getText().clear();
             }

             // Insert User Details Into Room Database...
             Log.d(TAG, "Inserting user details in database...");
             mUserDetailsViewModel.insert(userDetails);


         }
     }
 }