package com.eflexsoft.chooigbowords;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.eflexsoft.chooigbowords.databinding.ActivityMainBinding;
import com.eflexsoft.chooigbowords.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.frame_container);

        NavController navController = navHostFragment.getNavController();

//        FragmentName fragmentName = new FragmentName();
//        fragmentName.setFragmentName("Choo igbo");
//
//        activityMainBinding.setFragmentName(fragmentName);
//
//        List<String> strings = new ArrayList<>();
//        strings.add("Search in igbo");
//        strings.add("Result in english");
//
//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(new SearchFragment());
//        fragments.add(new ResultsFragment());
//
//        VPagerAdapter vPagerAdapter = new VPagerAdapter(getSupportFragmentManager(),strings,fragments);

//        activityMainBinding.viewPager.setAdapter(vPagerAdapter);
//        activityMainBinding.tabLay.setupWithViewPager(activityMainBinding.viewPager);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for(Fragment fragment : getSupportFragmentManager().getFragments()){
//            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}