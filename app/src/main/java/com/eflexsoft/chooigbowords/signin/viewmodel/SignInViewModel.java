package com.eflexsoft.chooigbowords.signin.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eflexsoft.chooigbowords.signin.repository.SignInRepository;
import com.google.firebase.auth.AuthCredential;

public class SignInViewModel extends AndroidViewModel {

    SignInRepository repository;

    public SignInViewModel(@NonNull Application application) {
        super(application);

            repository = new SignInRepository();

    }

    public void doGoogleAuth(AuthCredential authCredential, Context context) {

        repository.doGoogleAuth(authCredential, context);

    }

    public LiveData<Boolean> observeGoogleAuthStatus(){

        return repository.isSignInSuccessful;

    }

}
