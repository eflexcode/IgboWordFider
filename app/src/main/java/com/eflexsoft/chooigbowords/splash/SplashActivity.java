package com.eflexsoft.chooigbowords.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.eflexsoft.chooigbowords.MainActivity;
import com.eflexsoft.chooigbowords.R;
import com.eflexsoft.chooigbowords.signin.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                if (firebaseUser != null){
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));

                }else {
                    startActivity(new Intent(SplashActivity.this, SignInActivity.class));
                }
finish();
            }
        },10000);

    }
}