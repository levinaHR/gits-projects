package com.levinahr.asus.tugas12.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.levinahr.asus.tugas12.R;
import com.levinahr.asus.tugas12.adapter.MovieAdapter;
import com.levinahr.asus.tugas12.database.FavoriteDb;
import com.levinahr.asus.tugas12.model.MovieModel;

import java.util.List;

public class FavoriteFragment extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    Button Clear;
    FavoriteDb favoriteDb;
    List<MovieModel> favList;
    MovieAdapter movieAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        Context context = view.getContext();

        recyclerView = view.findViewById(R.id.movie_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        favoriteDb = Room.databaseBuilder(context, FavoriteDb.class, "favoritedb").allowMainThreadQueries().build();
        favList = favoriteDb.myDao().getMovieList();
        movieAdapter = new MovieAdapter(getActivity(), favList);
        recyclerView.setAdapter(movieAdapter);

        Clear = view.findViewById(R.id.favorite_clear);
        Clear.setOnClickListener(this);

        return view;
    }

    private void clearFavorites() {
        favoriteDb.myDao().nukeTable();
        favList = favoriteDb.myDao().getMovieList();
        movieAdapter = new MovieAdapter(getActivity(), favList);
        recyclerView.setAdapter(movieAdapter);

        Toast.makeText(getContext(), "Favorites nuked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        clearFavorites();
    }

    @Override
    public void onResume() {
        super.onResume();

        favList = favoriteDb.myDao().getMovieList();
        movieAdapter = new MovieAdapter(getActivity(), favList);
        recyclerView.setAdapter(movieAdapter);
    }
}