package com.example.tugas9;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    Context context;
    RecyclerView recyclerView;
    List<Cat> catList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.cat_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        createCatList();

        CatRecyclerViewAdapter adapter = new CatRecyclerViewAdapter(catList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void createCatList() {
        String[] titles = {"The Owner", "The Chief Operator", "The Right Hand Man", "The Register", "The Barista", "The Waiter"};
        String[] descs = {"A rather curvaceous bundle of sugary-sweet ambition, Mitzi is Atlas' widow and the current owner of the Lackadaisy establishment.",
                "The imposing, glaring, surly, one-eyed Slovak with knees that won't bend, employee at the Lackadaisy Coffeeshop, and now under Mitzi May.",
                "A former employee of Lackadaisy in its glory days, he's been described as Atlas' \"Golden boy\" by Mitzi May.",
                "A bright, friendly girl who works behind the till at the Lackadaisy Cafe. Atlas May was a friend of her father and was her godfather.",
                "Rocky is, to put it mildly, eccentric, prone to rash behaviour, manic grins and general foolishness.",
                "He got the nickname Freckle as when he was very young, Rocky shaved all the fur on his face to discover that he had a single freckle."};
        int[] pics = {R.drawable.mitzy, R.drawable.viktor, R.drawable.mordecai, R.drawable.pepper, R.drawable.rocky, R.drawable.calvin};
        int count = 0;

        for (String title : titles) {
            Cat cat = new Cat(title, descs[count], pics[count]);
            catList.add(cat);
            count++;
        }
    }
}