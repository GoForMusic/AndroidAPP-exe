package com.example.androidapp_exe.ui.Home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Integer> name;

    public HomeViewModel(){
        name = new MutableLiveData<>(0);
    }

    public void Increment(){
        name.postValue(name.getValue()+1);
    }

    public LiveData<Integer> getText(){
        return name;
    }
}
