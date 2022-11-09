package com.example.androidapp_exe.ViewModel;


import android.app.Activity;
import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidapp_exe.Entity.User;
import com.example.androidapp_exe.Model.UserRepository;

import java.util.ArrayList;

public class HomeViewModel extends AndroidViewModel {
    UserRepository repository;

    public HomeViewModel(Application app){
        super(app);
        repository = UserRepository.getInstance(app);
    }

    public void registerAccount(Activity activity, User user, String password)
    {
        repository.registerAccount(activity,user,password);
    }

    public MutableLiveData<User> getUser(String uid)
    {
        return repository.getUser(uid);
    }

    public void removeUser(User user)
    {
        repository.removeUser(user);
    }

    public void updateUser(User user)
    {
        repository.updateUser(user);
    }

}
