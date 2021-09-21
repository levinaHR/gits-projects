package com.levinahr.asus.tugas12.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.levinahr.asus.tugas12.adapter.MovieAdapter;
import com.levinahr.asus.tugas12.R;
import com.levinahr.asus.tugas12.model.TopRatedResponse;
import com.levinahr.asus.tugas12.api.ApiService;
import com.levinahr.asus.tugas12.api.InitRetrofit;
import com.levinahr.asus.tugas12.model.MovieModel;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        Context context = view.getContext();

        recyclerView = view.findViewById(R.id.movie_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        getMovieTopRated();

        return view;
    }

    private void getMovieTopRated() {
        ApiService apiService = InitRetrofit.getInstance();
        Call<TopRatedResponse> responseCall = apiService.top_rated();

        responseCall.enqueue(new Callback<TopRatedResponse>() {
            @Override
            public void onResponse(@NonNull Call<TopRatedResponse> call, @NonNull Response<TopRatedResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("movie", Objects.requireNonNull(response.body()).getResults().toString());
                    ArrayList<MovieModel> movieModels = (ArrayList<MovieModel>) response.body().getResults();
                    MovieAdapter movieAdapter = new MovieAdapter(getActivity(), movieModels);

                    recyclerView.setAdapter(movieAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TopRatedResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}