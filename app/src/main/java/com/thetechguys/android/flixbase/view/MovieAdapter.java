package com.thetechguys.android.flixbase.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thetechguys.android.flixbase.R;
import com.thetechguys.android.flixbase.model.MovieData;
import com.thetechguys.android.flixbase.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieData> movies = new ArrayList<>();
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_title) TextView mMovietitle;
        @BindView(R.id.movie_image) ImageView mMoviePoster;
        View mRoot;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mRoot = view;
        }
    }

    public MovieAdapter(List<MovieData> movieDatas){
        movies = movieDatas;
        //this.context = context;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      MovieData item = movies.get(position);
        context = holder.mRoot.getContext();

        holder.mMovietitle.setText(item.getTitle());

        String movieImageUrl = Constants.MOVIE_DB_POSTER_URL + Constants.POSTER_PHONE_SIZE + item.getPosterPath();

        Picasso.with(context).load(movieImageUrl).placeholder(R.color.colorPrimary).into(holder.mMoviePoster);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}