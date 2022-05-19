package com.eflexsoft.chooigbowords.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.eflexsoft.chooigbowords.R;
import com.eflexsoft.chooigbowords.adapters.ResultAdapter;
import com.eflexsoft.chooigbowords.databinding.FragmentResultsBinding;
import com.eflexsoft.chooigbowords.model.IgboApiResponse;
import com.eflexsoft.chooigbowords.viewmodel.HomeViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class ResultsFragment extends BottomSheetDialogFragment {

    HomeViewModel viewModel;

    FragmentResultsBinding binding;
    String definition;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        Bundle bundle = getArguments();
        String keyword = bundle.getString("keyword");

        viewModel.doSearch(keyword.trim());

        binding = FragmentResultsBinding.inflate(inflater, container, false);

        binding.resultCount.setText("Getting result for " +keyword);

        binding.resultRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.resultRecyclerView.showShimmer();

        viewModel.observeIgboApiResponse().observe(getViewLifecycleOwner(), new Observer<List<IgboApiResponse>>() {
            @Override
            public void onChanged(List<IgboApiResponse> igboApiResponses) {

                binding.resultCount.setText("About " + igboApiResponses.size() + " results found for " + keyword);
                binding.resultRecyclerView.hideShimmer();

                if (igboApiResponses.size() > 0) {

                    ResultAdapter resultAdapter = new ResultAdapter(igboApiResponses, getContext());
                    binding.resultRecyclerView.setAdapter(resultAdapter);

                    binding.resultRecyclerView.setVisibility(View.VISIBLE);
                    binding.error.setVisibility(View.GONE);
                    binding.errorText.setVisibility(View.GONE);

                } else {
                    binding.resultRecyclerView.setVisibility(View.GONE);
                    binding.error.setVisibility(View.VISIBLE);
                    binding.errorText.setVisibility(View.VISIBLE);
                }

            }
        });

        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(getActivity(), R.id.frame_container).navigateUp();

            }
        });

        return binding.getRoot();
    }
}