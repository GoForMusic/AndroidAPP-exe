package com.example.androidapp_exe.Model;

import android.app.Activity;
import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapp_exe.Entity.User;
import com.example.androidapp_exe.Model.DAO.UserDAO;

public class UserRepository {
    private static UserRepository instance;
    private static UserDAO userDAO;

    private UserRepository(Application app) {
        userDAO = UserDAO.getInstance(app);
    }


    public static UserRepository getInstance(Application app) {
        if (instance == null) {
            instance = new UserRepository(app);
        }
        return instance;
    }


    public void registerAccount(Activity activity, User user, String password) {
        userDAO.addUser(activity, user, password);
    }

    public MutableLiveData<User> getUser(String uid) {
        return userDAO.getUser(uid);
    }

    public void removeUser(User user)
    {
        userDAO.removeUser(user);
    }

    public void updateUser(User user)
    {
        userDAO.updateUser(user);
    }
}
