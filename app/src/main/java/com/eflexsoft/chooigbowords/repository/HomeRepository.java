package com.eflexsoft.chooigbowords.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.eflexsoft.chooigbowords.IgboApi;
import com.eflexsoft.chooigbowords.model.IgboApiResponse;
import com.eflexsoft.chooigbowords.model.SearchHistory;
import com.eflexsoft.chooigbowords.retrofit.RetrofitClients;
import com.eflexsoft.chooigbowords.util.Util;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    public MutableLiveData<Boolean> isSearchSuccessfully = new MutableLiveData<>();
    public MutableLiveData<List<IgboApiResponse>> igboApiResponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<SearchHistory>> historyMutableLiveData = new MutableLiveData<>();
    private static final String TAG = "HomeRepository";

    public void doSearch(String keyword) {

        //https://igboapi.com/api/v1/words?keyword=please&page=0&range=1&strict=true&dialects=true&examples=true&isStandardIgbo=true&pronunciation=true&nsibidi=false

        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("page", 0);
        map.put("range", 1);
        map.put("strict", true);
        map.put("dialects", false);
        map.put("examples", true);
        map.put("isStandardIgbo", true);
        map.put("nsibidi", false);

        RetrofitClients retrofitClients = RetrofitClients.retrofitClientsInstance();

        IgboApi api = retrofitClients.getRetrofit().create(IgboApi.class);

        api.IGBO_API_RESPONSE_CALL(map).enqueue(new Callback<List<IgboApiResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<IgboApiResponse>> call, @NonNull Response<List<IgboApiResponse>> response) {
                Log.d(TAG, "onResponse: "+response.code());

                if (response.code() == 200) {


                    isSearchSuccessfully.setValue(true);
                    igboApiResponseMutableLiveData.setValue(response.body());
                    Log.d(TAG, "onResponse: " + response.code());


                } else {
                    isSearchSuccessfully.setValue(false);
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<IgboApiResponse>> call, @NonNull Throwable t) {
                isSearchSuccessfully.setValue(false);
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

    public void getSearchedHistory() {

        Query query = FirebaseFirestore.getInstance().collection(Util.USER_COLLECTION_REFERENCE).document(FirebaseAuth.getInstance().getUid())
                .collection(Util.SEARCH_HISTORY).orderBy("id", Query.Direction.DESCENDING);


        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                List<SearchHistory> searchHistories = new ArrayList<>();

                for (DocumentSnapshot documentSnapshot : value.getDocuments()) {

                    SearchHistory history = documentSnapshot.toObject(SearchHistory.class);

                    searchHistories.add(history);

                }

                historyMutableLiveData.setValue(searchHistories);

            }
        });


    }

}
