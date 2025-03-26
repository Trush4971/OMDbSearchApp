package com.example.omdbsearchapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private EditText searchInput;
    private Button searchButton;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private SearchResultAdapter adapter;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchInput = findViewById(R.id.search_input);
        searchButton = findViewById(R.id.search_button);
        progressBar = findViewById(R.id.loading_indicator);
        recyclerView = findViewById(R.id.recyclerView);

        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        adapter = new SearchResultAdapter(result -> {
            // Fetch full movie details using the result's title
            viewModel.getMovieDetails(result.getTitle()).observe(this, movie -> {
                if (movie != null) {
                    // Launch detail activity with movie data
                    Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                    intent.putExtra("title", movie.getTitle());
                    intent.putExtra("year", movie.getYear());
                    intent.putExtra("genre", movie.getGenre());
                    intent.putExtra("director", movie.getDirector());
                    intent.putExtra("actors", movie.getActors());
                    intent.putExtra("plot", movie.getPlot());
                    intent.putExtra("poster", movie.getPoster());
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Details not found", Toast.LENGTH_SHORT).show();
                }
            });
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(v -> {
            String query = searchInput.getText().toString().trim();
            if (!query.isEmpty()) {
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);

                viewModel.searchMovies(query).observe(this, results -> {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    if (results != null && !results.isEmpty()) {
                        adapter.submitList(results);  // ListAdapter usage
                    } else {
                        Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Please enter a movie title", Toast.LENGTH_SHORT).show();
            }
        });
    }
}