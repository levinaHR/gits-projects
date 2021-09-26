package com.levinahr.asus.catdoption.activity.ui.cat;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.levinahr.asus.catdoption.R;
import com.levinahr.asus.catdoption.adapter.CatAdapter;
import com.levinahr.asus.catdoption.model.CatModel;

import java.util.ArrayList;
import java.util.List;

public class CatFragment extends Fragment {
    private String TAG = "mvvm";
    private RecyclerView rv;
    private CatAdapter catAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cat, container, false);
        List<CatModel> catList = new ArrayList<>();

        rv = view.findViewById(R.id.rv_cat);
        rv.setAdapter(catAdapter);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        CatViewModel catViewModel = ViewModelProviders.of(this).get(CatViewModel.class);
        catViewModel.getCat().observe(this, new Observer<List<CatModel>>() {
            @Override
            public void onChanged(List<CatModel> catList) {
                catAdapter = new CatAdapter(getActivity(), catList);
                rv.setAdapter(catAdapter);
            }
        });

        return view;
    }
}