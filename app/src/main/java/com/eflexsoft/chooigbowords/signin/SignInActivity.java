package com.eflexsoft.chooigbowords.signin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.eflexsoft.chooigbowords.MainActivity;
import com.eflexsoft.chooigbowords.R;
import com.eflexsoft.chooigbowords.databinding.ActivitySignInBinding;
import com.eflexsoft.chooigbowords.signin.viewmodel.SignInViewModel;
import com.eflexsoft.chooigbowords.util.Util;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    ProgressDialog progressDialog;
    SignInViewModel viewModel;

    String token = "363331273249-hbflvd1qp35df81om9bjg2pg1mbi14m0.apps.googleusercontent.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        googleSignInOptions = new GoogleSignInOptions.Builder()
                .requestEmail()
                .requestProfile()
                .requestIdToken(getString(R.string.default_web_client_id))
                .build();

        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Connecting your google account please wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        binding.authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, Util.GOOGLE_AUTH_ACTIVITY_REQUEST_CODE);

            }
        });

        viewModel.observeGoogleAuthStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                if (aBoolean){

                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    finish();

                }else {
                    progressDialog.cancel();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (Util.GOOGLE_AUTH_ACTIVITY_REQUEST_CODE == requestCode){

            Toast.makeText(this, "Connecting your google account", Toast.LENGTH_SHORT).show();

            try {

                Task<GoogleSignInAccount> googleSignInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);

                GoogleSignInAccount googleSignInAccount = googleSignInAccountTask.getResult(ApiException.class);

                AuthCredential authCredential= GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(),null);

                viewModel.doGoogleAuth(authCredential,this);
                progressDialog.show();

            }catch (Exception e){
                Toast.makeText(this, "Connecting your google account failed", Toast.LENGTH_SHORT).show();
            }

        }

    }
}