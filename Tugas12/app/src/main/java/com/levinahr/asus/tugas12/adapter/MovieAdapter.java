package com.levinahr.asus.tugas12.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.levinahr.asus.tugas12.BuildConfig;
import com.levinahr.asus.tugas12.activity.MovieDetailActivity;
import com.levinahr.asus.tugas12.R;
import com.levinahr.asus.tugas12.model.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    Context movieContext;
    List<MovieModel> movieList = new ArrayList<>();

    public MovieAdapter(Context movieContext, List<MovieModel> movieList) {
        this.movieContext = movieContext;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(movieContext);
        View view = inflater.inflate(R.layout.fragment_movie_item, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieModel movie = movieList.get(position);
        holder.title.setText(movie.getTitle());

        // Glide to display image
        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.background);

        Glide.with(movieContext)
                .setDefaultRequestOptions(requestOptions)
                .load(BuildConfig.IMAGE + movieList.get(position).getPosterPath())
                .into(holder.image);

        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(movieContext, MovieDetailActivity.class);
            intent.putExtra("backdrop", BuildConfig.IMAGE + movieList.get(position).getBackdropPath());
            intent.putExtra("detail", movieList.get(position).getOverview());
            intent.putExtra("title", movieList.get(position).getTitle());

            movieContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        CardView cardView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.movie_title);
            image = itemView.findViewById(R.id.movie_image);
            cardView = itemView.findViewById(R.id.movie_cv);
        }
    }
}
