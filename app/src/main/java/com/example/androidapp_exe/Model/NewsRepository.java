package com.example.androidapp_exe.Model;

import android.util.Log;
import android.widget.Adapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidapp_exe.Entity.News;
import com.example.androidapp_exe.Entity.NewsResponse;
import com.example.androidapp_exe.ViewModel.AdapterNews;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class NewsRepository {
    private static NewsRepository instance;
    private final MutableLiveData<NewsResponse> newsMutableLiveData;

    private NewsRepository() {
        newsMutableLiveData = new MutableLiveData<>();

    }

    public static synchronized NewsRepository getInstance() {
        if (instance == null) {
            instance = new NewsRepository();
        }
        return instance;
    }

    public LiveData<NewsResponse> getNews(String country, int size, AdapterNews adapterNews) {
        findNews(country, size, adapterNews);
        return newsMutableLiveData;
    }

    private void findNews(String country, int size, AdapterNews adapterNews) {
        ApiInterface apiInterface = ApiUtilities.getApiInterface();
        Call<NewsResponse> call = apiInterface.getNews(country,size,ApiUtilities.getAPIKey());
        call.enqueue(new Callback<NewsResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(response.isSuccessful()){
                    newsMutableLiveData.postValue(response.body());
                    adapterNews.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });

    }
}
