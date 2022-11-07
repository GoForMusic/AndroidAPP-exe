package com.example.androidapp_exe.ui.Home;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidapp_exe.Entity.News;
import com.example.androidapp_exe.Model.ApiUtilities;
import com.example.androidapp_exe.R;
import com.example.androidapp_exe.ViewModel.AdapterNews;
import com.example.androidapp_exe.ViewModel.HomeViewModel;
import com.example.androidapp_exe.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private RecyclerView recyclerViewOfHome;
    private AdapterNews adapter;

    private ArrayList<News> modelArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerViewOfHome = root.findViewById(R.id.idNewsItems);
        modelArrayList= new ArrayList<>();
        adapter = new AdapterNews(root.getContext(), modelArrayList);

        //-----
        homeViewModel.getNews("us",10,adapter).observe(getViewLifecycleOwner(),article->{
                try {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext(), RecyclerView.VERTICAL, false);
                    recyclerViewOfHome.setLayoutManager(linearLayoutManager);
                    recyclerViewOfHome.setAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error getting reviews data: " + e.getMessage());
                }
        });

        return root;
    }


}
