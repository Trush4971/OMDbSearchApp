package com.example.omdbsearchapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class SearchResultAdapter extends ListAdapter<SearchResult, SearchResultAdapter.SearchViewHolder> {

    public interface OnItemClickListener {
        void onFilmClick(SearchResult result);
    }

    private final OnItemClickListener listener;

    public SearchResultAdapter(OnItemClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    private static final DiffUtil.ItemCallback<SearchResult> DIFF_CALLBACK = new DiffUtil.ItemCallback<SearchResult>() {
        @Override
        public boolean areItemsTheSame(@NonNull SearchResult oldItem, @NonNull SearchResult newItem) {
            return oldItem.getImdbID().equals(newItem.getImdbID());
        }

        @Override
        public boolean areContentsTheSame(@NonNull SearchResult oldItem, @NonNull SearchResult newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getYear().equals(newItem.getYear()) &&
                    oldItem.getPoster().equals(newItem.getPoster());
        }
    };

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        SearchResult result = getItem(position);
        holder.titleText.setText(result.getTitle());
        holder.yearText.setText(result.getYear());

        Glide.with(holder.itemView.getContext())
                .load(result.getPoster())
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(holder.posterImage);

        holder.itemView.setOnClickListener(v -> listener.onFilmClick(result));
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, yearText;
        ImageView posterImage;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.search_title);
            yearText = itemView.findViewById(R.id.search_year);
            posterImage = itemView.findViewById(R.id.search_poster);
        }
    }
}