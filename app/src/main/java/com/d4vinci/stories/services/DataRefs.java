package com.d4vinci.stories.services;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.d4vinci.stories.models.Story;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by D4Vinci on 7/24/2017.
 */

public class DataRefs {

    private final String TAG = "DataRef";

    private static final DataRefs ourInstance = new DataRefs();

    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference storiesRef = rootRef.child("stories");
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    private StorageReference storiesStorage = storageRef.child("stories");

    public static DataRefs getInstance() {
        return ourInstance;
    }

    private DataRefs() {
    }

    public DatabaseReference getRootRef(){
        return rootRef;
    }

    public DatabaseReference getStoriesRef() {
        return rootRef.child("stories");
    }

    public void uploadStory(final String text, final String photo) {
//        Uri file = Uri.fromFile(new File(photo));
        UploadTask task = storiesStorage.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(Uri.parse(photo).getLastPathSegment()).putFile(Uri.parse(photo));

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: Upload failed");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storiesRef.push().setValue(new Story(text, FirebaseAuth.getInstance().getCurrentUser().getUid(), taskSnapshot.getDownloadUrl().toString()));
                Log.d(TAG, "onSuccess: uploaded: "+ photo);
            }
        });
    }

    public DatabaseReference getUsersRef() {
        return rootRef.child("users");
    }
}
