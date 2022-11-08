package com.example.androidapp_exe.Model.DAO;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapp_exe.Entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private final UserLiveData currentUser;
    private final Application app;
    private static UserDAO instance;

    // -- Live data
    private MutableLiveData<String> authenticationMessage = new MutableLiveData<>("");
    //sign out
    private MutableLiveData<Boolean> signOut = new MutableLiveData<>(false);
    private MutableLiveData<User> user = new MutableLiveData<>(null);



    //
    //Authentication
    private FirebaseAuth firebaseAuth;
    //Firebase Database
    private FirebaseFirestore firebaseDatabase;

    User returnedUser;
    User userModal = new User();

    private UserDAO(Application app) {
        this.app = app;
        currentUser = new UserLiveData();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseFirestore.getInstance();
    }

    public static UserDAO getInstance(Application app) {
        if (instance == null) {
            instance = new UserDAO(app);
        }
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void registerAccount(Activity activity, User user, String password) {
        try{
            firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), password)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success
                                Log.d(TAG, "createUserWithEmail:success");
                                authenticationMessage.postValue("User created!");
                                createUser(firebaseAuth.getCurrentUser().getUid(), user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                authenticationMessage.postValue("Error on creating user");
                            }
                        }
                    });
            signOut.postValue(false);
        }
        catch (Exception e)
        {
            authenticationMessage.postValue("Exception: " + e.getMessage());
        }
    }


    public void createUser(String uid, User userParam) {

        User user = new User();
        user.setEmail(userParam.getEmail());
        user.setLastName(userParam.getLastName());
        user.setFirstName(userParam.getFirstName());
        user.setUid(uid);
        user.setWalletUid(null);

        firebaseDatabase.collection("users").document(uid).set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "User created successfully!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing the user", e);
                    }
                });
    }

    public LiveData<User> getUser() {
        return user;
    }

    public User getUserModal(String uid) {
        DocumentReference docRef = firebaseDatabase.collection("users").document(uid);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                userModal = documentSnapshot.toObject(User.class);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, e.getMessage());
            }
        });
        return userModal;
    }

}
