package com.eflexsoft.chooigbowords;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.eflexsoft.chooigbowords.databinding.ActivityImageFullScreenBinding;

public class ImageFullScreenActivity extends AppCompatActivity {

    ActivityImageFullScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageFullScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String url = getIntent().getStringExtra("url");
        Glide.with(this).load(url).into(binding.image);


    }
}