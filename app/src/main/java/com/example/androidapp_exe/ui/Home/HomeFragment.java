package com.example.androidapp_exe.ui.Home;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp_exe.Entity.User;
import com.example.androidapp_exe.R;
import com.example.androidapp_exe.ViewModel.HomeViewModel;
import com.example.androidapp_exe.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    private Button registerButton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        registerButton  = root.findViewById(R.id.button);

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    User local = new User();
                    local.setFirstName("Militaru");
                    local.setLastName("Adrian");
                    local.setEmail("goformusicro@gmail.com");
                    local.setWalletUid(null);
                    homeViewModel.registerAccount((Activity) getView().getContext(),local,"test1234567");
                }catch (NullPointerException e){
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }
}
