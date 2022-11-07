package com.example.androidapp_exe.ViewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidapp_exe.Entity.News;
import com.example.androidapp_exe.Entity.NewsResponse;
import com.example.androidapp_exe.Model.NewsRepository;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    NewsRepository repository;

    public HomeViewModel(){
        repository = NewsRepository.getInstance();
    }

    public LiveData<NewsResponse> getNews(String country, int size, AdapterNews adapterNews){
        return repository.getNews(country, size, adapterNews);
    }
}
