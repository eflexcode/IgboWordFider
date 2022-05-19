package com.eflexsoft.chooigbowords.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eflexsoft.chooigbowords.IgboApi;
import com.eflexsoft.chooigbowords.R;
import com.eflexsoft.chooigbowords.databinding.FragmentSearchBinding;
import com.eflexsoft.chooigbowords.model.IgboApiResponse;
import com.eflexsoft.chooigbowords.retrofit.RetrofitClients;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {

    FragmentSearchBinding fragmentSearchBinding;
    IgboApi igboApi;
    private static final String TAG = "SearchFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);

        RetrofitClients retrofitClients = RetrofitClients.retrofitClientsInstance();

        igboApi = retrofitClients.getRetrofit().create(IgboApi.class);

        View view = fragmentSearchBinding.getRoot();

//        fragmentSearchBinding.btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String keyWord = fragmentSearchBinding.searchForUserWithId.getText().toString();
//
//                if (!keyWord.trim().isEmpty()) {
//                    fragmentSearchBinding.btnSearch.startAnimation();
//
//                    doWordSearch(keyWord);
//
//                }
//            }
//        });

        return view;
    }

    public void doWordSearch(String keyWord) {

//        igboApi.IGBO_API_RESPONSE_CALL(keyWord).enqueue(new Callback<List<IgboApiResponse>>() {
//            @Override
//            public void onResponse(Call<List<IgboApiResponse>> call, Response<List<IgboApiResponse>> response) {
//                if (response.isSuccessful() && response.body() != null) {
////                    Toast.makeText(getContext(), response.body().get(0).getWord(), Toast.LENGTH_SHORT).show();
////                    fragmentSearchBinding.btnSearch.revertAnimation();
////                    Toast.makeText(getContext(), (Integer) response.body().getDefinitions().get(0), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<IgboApiResponse>> call, Throwable t) {
////                fragmentSearchBinding.btnSearch.revertAnimation();
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onFailure: " + t.getMessage());
//            }
//        });

    }
// fragmentSearchBinding.btnSearch.revertAnimation();
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onFailure: "+t.getMessage());


//
}