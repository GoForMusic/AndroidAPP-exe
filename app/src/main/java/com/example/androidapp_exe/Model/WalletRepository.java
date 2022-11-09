package com.example.androidapp_exe.Model;

import android.app.Application;

import com.example.androidapp_exe.Model.DAO.WalletDAO;

public class WalletRepository {
    private static WalletRepository instance;
    private static WalletDAO walletDAO;

    private WalletRepository(Application app){
        walletDAO = WalletDAO.getInstance(app);
    }

    public static WalletRepository getInstance(Application app){
        if (instance == null) {
            instance = new WalletRepository(app);
        }
        return instance;
    }

    // implementations from Interface maybee?

}
