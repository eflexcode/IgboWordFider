package com.eflexsoft.chooigbowords.fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.eflexsoft.chooigbowords.R;
import com.eflexsoft.chooigbowords.adapters.HistoryAdapter;
import com.eflexsoft.chooigbowords.databinding.FragmentHomeBinding;
import com.eflexsoft.chooigbowords.model.SearchHistory;
import com.eflexsoft.chooigbowords.model.User;
import com.eflexsoft.chooigbowords.util.Util;
import com.eflexsoft.chooigbowords.viewmodel.HomeViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    HomeViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding.search.requestFocus();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {

            DocumentReference reference = FirebaseFirestore.getInstance().collection("Users").document(firebaseUser.getUid());

            reference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                    User user = value.toObject(User.class);

                    try {

                        Glide.with(getContext()).load(user.getProfileImageUrl()).into(binding.profilePicture);

                    } catch (Exception e) {

                    }

                }
            });
        }

        binding.search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    String keyword = v.getText().toString();

                    if (!keyword.trim().isEmpty()) {

//                        viewModel.doSearch(keyword);

                        Bundle bundle = new Bundle();
                        bundle.putString("keyword", keyword);

                        Navigation.findNavController(getActivity(), R.id.frame_container).navigate(R.id.action_homeFragment_to_resultsFragment, bundle);

                        String id = String.valueOf(System.currentTimeMillis());

//                        DocumentReference reference = FirebaseFirestore.getInstance().collection(Util.USER_COLLECTION_REFERENCE)
//                                .document(Util.SEARCH_HISTORY).collection(Util.HISTORY_LIST).document(id);

                        DocumentReference reference = FirebaseFirestore.getInstance().collection(Util.USER_COLLECTION_REFERENCE).document(FirebaseAuth.getInstance().getUid())
                                .collection(Util.SEARCH_HISTORY).document(id);


                        SearchHistory searchHistory = new SearchHistory(id, keyword);

                        reference.set(searchHistory);

                    }

                }


                return true;
            }
        });

        binding.profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(getActivity(), R.id.frame_container).navigate(R.id.action_homeFragment_to_accountFragment);

            }
        });

//        viewModel.observeIgboApiResponse().observe(getViewLifecycleOwner(), new Observer<IgboApiResponse>() {
//            @Override
//            public void onChanged(IgboApiResponse igboApiResponse) {
//                Toast.makeText(getContext(), igboApiResponse.getWord(), Toast.LENGTH_LONG).show();
//
//            }
//        });

        viewModel.getSearchedHistory();

        binding.resultRecyclerView.showShimmer();
        binding.resultRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.observeSearchHistory().observe(getViewLifecycleOwner(), new Observer<List<SearchHistory>>() {
            @Override
            public void onChanged(List<SearchHistory> searchHistories) {

                binding.resultRecyclerView.hideShimmer();

                if (searchHistories.size() > 0) {

                    HistoryAdapter historyAdapter = new HistoryAdapter(searchHistories,getContext());

                    binding.resultRecyclerView.setAdapter(historyAdapter);

                    binding.error.setVisibility(View.GONE);
                    binding.errorText.setVisibility(View.GONE);

                }else {

                    binding.error.setVisibility(View.VISIBLE);
                    binding.errorText.setVisibility(View.VISIBLE);

                }

            }
        });

        return binding.getRoot();
    }
}