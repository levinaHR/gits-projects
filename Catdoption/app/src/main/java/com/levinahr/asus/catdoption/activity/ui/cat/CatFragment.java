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
import com.levinahr.asus.catdoption.adapter.ImgSliderAdapter;
import com.levinahr.asus.catdoption.model.CatModel;
import com.levinahr.asus.catdoption.model.SliderModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class CatFragment extends Fragment {
    private String TAG = "mvvm";
    private RecyclerView rv;
    private CatAdapter catAdapter;
    private List<SliderModel> sliderModels;

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

        SliderView sliderView = view.findViewById(R.id.imageSlider);
        loadImageSlider();
        sliderView.setSliderAdapter(new ImgSliderAdapter(getContext(), sliderModels));

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setScrollTimeInSec(5);
        sliderView.startAutoCycle();

        return view;
    }

    private void loadImageSlider() {
        sliderModels = new ArrayList<>();

        String[] urls = {"https://i.ibb.co/ZV4yNL0/IMG-20190418-150054.jpg",
                         "https://i.ibb.co/wBWQGnC/IMG-20190418-145616.jpg",
                         "https://i.ibb.co/BjB6GWH/IMG-20191227-084248.jpg",
                         "https://i.ibb.co/R9xDHF3/IMG-20190521-145501.jpg"};
        String[] descs = {"Perpus UI tahun pertama kuliah",
                          "Perpus UI tahun pertama kuliah juga",
                          "Taman yang lumayan deket rumah",
                          "Jembatan Texas Fakultas Teknik"};
        int count = 0;

        for (String url : urls) {
            SliderModel sliderModel = new SliderModel(url, descs[count]);
            sliderModels.add(sliderModel);
            count++;
        }
    }
}