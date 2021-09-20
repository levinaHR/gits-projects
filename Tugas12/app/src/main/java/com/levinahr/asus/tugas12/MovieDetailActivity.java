package com.levinahr.asus.tugas12;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.levinahr.asus.tugas12.databinding.ActivityMovieDetailBinding;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView movieDetailImage;
    private TextView movieDetailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_detail);
        initView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        String backdropPath = getIntent().getStringExtra("backdrop");
        String detail = getIntent().getStringExtra("detail");

        Glide.with(this)
                .load(backdropPath)
                .into(movieDetailImage);
        movieDetailText.setText(detail);
    }

    private void initView() {
        movieDetailImage = findViewById(R.id.movie_detail_image);
        movieDetailText = findViewById(R.id.movie_detail_text);
    }
}