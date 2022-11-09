package com.example.androidapp_exe.Model.DAO;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapp_exe.Entity.User;

public interface IUserDAO {
    void addUser(Activity activity, User user, String password);
    void removeUser(User user);
    void updateUser(User newUser);
    MutableLiveData<User> getUser(String uid);
}


//User user = new User();
//        user.setEmail(userParam.getEmail());
//        user.setLastName(userParam.getLastName());
//        user.setFirstName(userParam.getFirstName());
//        user.setUid(uid);
//        user.setWalletUid(null);