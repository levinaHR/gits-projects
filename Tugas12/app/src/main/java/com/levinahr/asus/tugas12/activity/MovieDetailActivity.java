package com.levinahr.asus.tugas12.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.levinahr.asus.tugas12.R;
import com.levinahr.asus.tugas12.api.ApiService;
import com.levinahr.asus.tugas12.api.InitRetrofit;
import com.levinahr.asus.tugas12.database.FavoriteDb;
import com.levinahr.asus.tugas12.model.MovieModel;
import com.levinahr.asus.tugas12.model.TopRatedResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView movieDetailImage;
    private TextView movieDetailText;
    public static FavoriteDb favoriteDb;
    ArrayList<MovieModel> movieModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_detail);
        initView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        String backdropPath = getIntent().getStringExtra("backdrop");
        String detail = getIntent().getStringExtra("detail");
        String title = getIntent().getStringExtra("title");

        Glide.with(this)
                .load(backdropPath)
                .into(movieDetailImage);
        movieDetailText.setText(detail);

        favoriteDb = Room.databaseBuilder(getApplicationContext(), FavoriteDb.class, "favoritedb").allowMainThreadQueries().build();
        getMovieTopRated();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<MovieModel> favs = favoriteDb.myDao().getMovieList();

                for (MovieModel movie : movieModels) {
                    if (movie.getTitle().equals(title)) {
//                        if (!favs.contains(movie)) {    //entah kenapa MovieModel@blabla nya berubah mulu jadi selalu true
//                            favoriteDb.myDao().addMovie(movie);
//                            Toast.makeText(view.getContext(), "Movie added to favorites", Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            favoriteDb.myDao().deleteMovie(movie);
//                            Toast.makeText(view.getContext(), "Movie removed from favorites", Toast.LENGTH_SHORT).show();
//                        }

                        if (favs.size() != 0) {
                            for (MovieModel fav : favs) {
                                if (fav.getTitle().equals(movie.getTitle())) {
                                    favoriteDb.myDao().deleteMovie(movie);
                                    Toast.makeText(view.getContext(), "Movie removed from favorites", Toast.LENGTH_SHORT).show();

                                } else {
                                    favoriteDb.myDao().addMovie(movie);
                                    Toast.makeText(view.getContext(), "Movie added to favorites", Toast.LENGTH_SHORT).show();
                                }

                                break;
                            }

                        } else {
                            favoriteDb.myDao().addMovie(movie);
                            Toast.makeText(view.getContext(), "Movie added to favorites", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    }
                }
            }
        });
    }

    private void initView() {
        movieDetailImage = findViewById(R.id.movie_detail_image);
        movieDetailText = findViewById(R.id.movie_detail_text);
    }

    private void getMovieTopRated() {
        ApiService apiService = InitRetrofit.getInstance();
        Call<TopRatedResponse> responseCall = apiService.top_rated();

        responseCall.enqueue(new Callback<TopRatedResponse>() {
            @Override
            public void onResponse(@NonNull Call<TopRatedResponse> call, @NonNull Response<TopRatedResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("movie", Objects.requireNonNull(response.body()).getResults().toString());
                    movieModels = (ArrayList<MovieModel>) response.body().getResults();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TopRatedResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}