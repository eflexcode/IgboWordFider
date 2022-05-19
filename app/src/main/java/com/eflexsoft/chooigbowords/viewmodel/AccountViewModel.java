package com.eflexsoft.chooigbowords.viewmodel;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.eflexsoft.chooigbowords.repository.AccountRepository;

public class AccountViewModel extends AndroidViewModel {

    AccountRepository repository;

    public AccountViewModel(@NonNull Application application) {
        super(application);
        repository = new AccountRepository();
    }

    public void saveChanges(Uri profileImageUrl, String userName, Context context) {
        repository.saveChanges(profileImageUrl, userName, context);
    }

    public void saveChanges(String userName, Context context) {

        repository.saveChanges(userName, context);

    }

}
