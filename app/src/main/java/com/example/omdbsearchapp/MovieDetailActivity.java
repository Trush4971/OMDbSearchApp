package com.example.omdbsearchapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView posterImage = findViewById(R.id.detail_poster);
        TextView titleText = findViewById(R.id.detail_title);
        TextView yearText = findViewById(R.id.detail_year);
        TextView genreText = findViewById(R.id.detail_genre);
        TextView directorText = findViewById(R.id.detail_director);
        TextView actorsText = findViewById(R.id.detail_actors);
        TextView plotText = findViewById(R.id.detail_plot);

        // Get data from Intent
        String title = getIntent().getStringExtra("title");
        String year = getIntent().getStringExtra("year");
        String genre = getIntent().getStringExtra("genre");
        String director = getIntent().getStringExtra("director");
        String actors = getIntent().getStringExtra("actors");
        String plot = getIntent().getStringExtra("plot");
        String posterUrl = getIntent().getStringExtra("poster");

        titleText.setText(title);
        yearText.setText("Year: " + year);
        genreText.setText("Genre: " + genre);
        directorText.setText("Director: " + director);
        actorsText.setText("Actors: " + actors);
        plotText.setText("Plot: " + plot);

        Glide.with(this)
                .load(posterUrl)
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(posterImage);
    }
}