package com.eflexsoft.chooigbowords.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.eflexsoft.chooigbowords.R;
import com.eflexsoft.chooigbowords.databinding.SearchHistoryItemBinding;
import com.eflexsoft.chooigbowords.model.SearchHistory;
import com.eflexsoft.chooigbowords.util.Util;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    List<SearchHistory> searchHistories;
    Context context;

    public HistoryAdapter(List<SearchHistory> searchHistories, Context context) {
        this.searchHistories = searchHistories;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        SearchHistoryItemBinding binding = SearchHistoryItemBinding.inflate(layoutInflater, parent, false);

        return new HistoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {

        SearchHistory history = searchHistories.get(position);

        holder.binding.keyword.setText(history.getKeyword());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("keyword", history.getKeyword());

                Navigation.findNavController((Activity) context, R.id.frame_container).navigate(R.id.action_homeFragment_to_resultsFragment, bundle);

            }
        });

        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(context)
                .setTitle("Warning!")
                .setMessage("Confirm Remover of " + history.getKeyword() + " from your search history?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DocumentReference reference = FirebaseFirestore.getInstance().collection(Util.USER_COLLECTION_REFERENCE).document(FirebaseAuth.getInstance().getUid())
                                .collection(Util.SEARCH_HISTORY).document(history.getId());

                        reference.delete();

                    }
                });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                alertDialogBuilder.show();

                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return searchHistories.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        SearchHistoryItemBinding binding;

        public HistoryViewHolder(@NonNull SearchHistoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
