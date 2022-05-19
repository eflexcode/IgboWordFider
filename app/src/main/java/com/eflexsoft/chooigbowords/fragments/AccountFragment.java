package com.eflexsoft.chooigbowords.fragments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.eflexsoft.chooigbowords.ImageFullScreenActivity;
import com.eflexsoft.chooigbowords.R;
import com.eflexsoft.chooigbowords.databinding.FragmentAccountBinding;
import com.eflexsoft.chooigbowords.model.User;
import com.eflexsoft.chooigbowords.util.Util;
import com.eflexsoft.chooigbowords.viewmodel.AccountViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class AccountFragment extends BottomSheetDialogFragment {

    FragmentAccountBinding binding;
    Uri uri;
    AccountViewModel viewModel;
    String profileImageUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {

            DocumentReference reference = FirebaseFirestore.getInstance().collection("Users").document(firebaseUser.getUid());

            reference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                    User user = value.toObject(User.class);

                    try {

                        Glide.with(getContext()).load(user.getProfileImageUrl()).into(binding.profilePicture);

                        binding.email.setText(user.getEmail());
                        binding.name.setText(user.getName());
                        profileImageUrl = user.getProfileImageUrl();

                    } catch (Exception e) {

                    }

                }
            });
        }

        binding.pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");

                    startActivityForResult(intent, Util.PICK_IMAGE_REQUEST_CODE);

                } else {

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 45);

                }
            }
        });

        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(getActivity(), R.id.frame_container).navigateUp();

            }
        });

        binding.profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), ImageFullScreenActivity.class).putExtra("url", profileImageUrl));

            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = binding.name.getText().toString();

                if (!username.trim().isEmpty()) {

                    if (uri == null) {
                        viewModel.saveChanges(username, getContext());
                    } else {
                        viewModel.saveChanges(uri, username, getContext());
                        Toast.makeText(getContext(), "Uploading image please wait", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Util.PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {

            uri = data.getData();
            binding.profilePicture.setImageURI(uri);

        }

    }
}