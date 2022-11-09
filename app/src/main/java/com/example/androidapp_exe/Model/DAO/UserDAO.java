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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.WriteResult;

import java.util.HashMap;
import java.util.Map;

public class UserDAO implements IUserDAO {
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
    MutableLiveData<User> userModal;

    private UserDAO(Application app) {
        this.app = app;
        currentUser = new UserLiveData();

        userModal=new MutableLiveData<>(new User());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseFirestore.getInstance();
    }

    public static UserDAO getInstance(Application app) {
        if (instance == null) {
            instance = new UserDAO(app);
        }
        return instance;
    }


    //


    private void createUser(String uid, User userParam) {

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

    @Override
    public void addUser(Activity activity, User user, String password) {
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

    @Override
    public void removeUser(User user) {
        firebaseDatabase.collection("users").document(user.getUid())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }

    @Override
    public void updateUser(User newUser) {

        DocumentReference docRef = firebaseDatabase.collection("users").document(newUser.getUid());
        //updates fields
        docRef.update("email",newUser.getEmail());
        docRef.update("lastName",newUser.getLastName());
        docRef.update("firstName",newUser.getFirstName());
        docRef.update("walletUid",newUser.getWalletUid());


    }

    @Override
    public MutableLiveData<User> getUser(String uid) {
             firebaseDatabase.collection("users").whereEqualTo("uid",uid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            User local = document.toObject(User.class);
                            userModal.postValue(local);
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });
            return userModal;
    }
}