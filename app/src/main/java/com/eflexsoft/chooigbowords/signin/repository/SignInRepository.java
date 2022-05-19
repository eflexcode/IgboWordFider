package com.eflexsoft.chooigbowords.signin.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.eflexsoft.chooigbowords.model.User;
import com.eflexsoft.chooigbowords.util.Util;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignInRepository {

    public MutableLiveData<Boolean> isSignInSuccessful = new MutableLiveData<>();

    public void doGoogleAuth(AuthCredential authCredential, Context context) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        boolean isNewUser = task.getResult().getAdditionalUserInfo().isNewUser();

                        if (isNewUser) {
                            GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(context);

                            String email = googleSignInAccount.getEmail();
                            String name = googleSignInAccount.getDisplayName();
                            String profileImageUrl = "";

                            if (FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl() != null) {

                                profileImageUrl = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();
                            }
                            String userId = FirebaseAuth.getInstance().getUid();

                            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

                            CollectionReference usersCollectionReference = firebaseFirestore.collection(Util.USER_COLLECTION_REFERENCE);

                            DocumentReference usersDocumentReference = usersCollectionReference.document(userId);

                            User user = new User(email, name, profileImageUrl, userId);

                            usersDocumentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    isSignInSuccessful.setValue(true);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    isSignInSuccessful.setValue(false);

                                }
                            });

                        } else {

                            isSignInSuccessful.setValue(true);

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                isSignInSuccessful.setValue(false);
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
