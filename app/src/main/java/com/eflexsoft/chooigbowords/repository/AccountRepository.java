package com.eflexsoft.chooigbowords.repository;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.eflexsoft.chooigbowords.util.Util;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {

    public void saveChanges(Uri profileImageUrl, String userName, Context context){

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

        StorageReference storageReference = firebaseStorage.getReference("profilePictures");
        StorageReference fileReference = storageReference.child(System.currentTimeMillis()+"");

        UploadTask uploadTask = fileReference.putFile(profileImageUrl);
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                if (!task.isSuccessful()){
                    throw task.getException();
                }

                return fileReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

                CollectionReference collectionReference = firebaseFirestore.collection(Util.USER_COLLECTION_REFERENCE);

                DocumentReference documentReference = collectionReference.document(FirebaseAuth.getInstance().getUid());

                Map<String,Object> map = new HashMap<>();
                map.put("name",userName);
                map.put("profileImageUrl",task.getResult().toString());

                documentReference.update(map);
                Toast.makeText(context, "Changed saved successfully", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failed to upload image", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void saveChanges(String userName,Context context){

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        CollectionReference collectionReference = firebaseFirestore.collection(Util.USER_COLLECTION_REFERENCE);

        DocumentReference documentReference = collectionReference.document(FirebaseAuth.getInstance().getUid());

        Map<String,Object> map = new HashMap<>();
        map.put("name",userName);

        documentReference.update(map);

        Toast.makeText(context, "Changed saved successfully", Toast.LENGTH_SHORT).show();

    }

}
