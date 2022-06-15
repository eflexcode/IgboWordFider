package com.eflexsoft.chooigbowords.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eflexsoft.chooigbowords.model.IgboApiResponse;
import com.eflexsoft.chooigbowords.model.SearchHistory;
import com.eflexsoft.chooigbowords.repository.HomeRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    HomeRepository repository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new HomeRepository();
    }

    public void doSearch(String keyword) {

        repository.doSearch(keyword);

    }

    public void getSearchedHistory() {
        repository.getSearchedHistory();
    }

    public LiveData<List<IgboApiResponse>> observeIgboApiResponse() {
        return repository.igboApiResponseMutableLiveData;
    }

    public LiveData<List<SearchHistory>> observeSearchHistory() {
        return repository.historyMutableLiveData;
    }

    public LiveData<Boolean> observeSuccess() {
        return repository.isSearchSuccessfully;
    }

}
