package com.example.androidapp_exe.Model.DAO;

import android.app.Application;

import com.google.firebase.firestore.FirebaseFirestore;

public class WalletDAO {
    private final Application app;
    private static WalletDAO instance;
    //Firebase Database
    private FirebaseFirestore firebaseDatabase;

    private WalletDAO(Application app){
        this.app = app;

        firebaseDatabase = FirebaseFirestore.getInstance();
    }

    public static WalletDAO getInstance(Application app)
    {
        if (instance == null) {
            instance = new WalletDAO(app);
        }
        return instance;
    }

    //imp

}
