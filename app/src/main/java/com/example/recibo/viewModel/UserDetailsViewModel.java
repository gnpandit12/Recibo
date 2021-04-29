package com.example.recibo.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.recibo.model.UserDetails;
import com.example.recibo.model.UserDetailsRepository;

public class UserDetailsViewModel extends AndroidViewModel {

    private static final String TAG = "MAIN_VIEW_MODEL";
    private UserDetailsRepository userDetailsRepository;
    private final LiveData<UserDetails> userDetailsLiveData;

    public UserDetailsViewModel(Application application) {
        super(application);
        userDetailsRepository = new UserDetailsRepository(application);
        userDetailsLiveData = userDetailsRepository.getUserDetails();
    }

    public LiveData<UserDetails> getUserDetails() { return userDetailsLiveData; }

    public void insert(UserDetails userDetails) { userDetailsRepository.insert(userDetails); }

}
