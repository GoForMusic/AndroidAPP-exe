package com.example.androidapp_exe.ui.Home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp_exe.Entity.User;
import com.example.androidapp_exe.R;
import com.example.androidapp_exe.ViewModel.HomeViewModel;
import com.example.androidapp_exe.databinding.FragmentPortfolioBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

public class PortfolioFragment extends Fragment {

    private FragmentPortfolioBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPortfolioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setUpGraph();

        return root;
    }

    private void setUpGraph(){

        long x =10;

        List<PieEntry> pieChartList = new ArrayList<>();
        List<Integer> colorsList = new ArrayList<>();

        pieChartList.add(new PieEntry(x,"X"));
        colorsList.add(getResources().getColor(R.color.purple_200));
        pieChartList.add(new PieEntry(5,"Y"));
        colorsList.add(getResources().getColor(R.color.black));

        PieDataSet pieDataSet = new PieDataSet(pieChartList,"");
        pieDataSet.setColors(colorsList);
        pieDataSet.setDrawIcons(false);
        pieDataSet.setSliceSpace(10f);
        pieDataSet.setIconsOffset(new MPPointF(0, 40));
        pieDataSet.setSelectionShift(3f);
        PieData pieData = new PieData(pieDataSet);

        binding.portfolioChart.setData(pieData);
        binding.portfolioChart.invalidate();
    }

}
