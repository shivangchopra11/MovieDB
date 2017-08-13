package com.example.shivang.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivang on 09/08/17.
 */



public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.MovieViewHolder> {

    private Context mContext;
    private List<Movies> mMovies;
    private MovieClickListener mListener;
    private LayoutInflater mInflater;

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.row_layout,parent,false);
        return new MovieViewHolder(itemView,mListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        Movies movie = mMovies.get(position);
        holder.titleTextView.setText(movie.getTitle());
        Picasso.with(mContext).load(movie.getPoster()).resize(2000, 900).centerInside().into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public interface MovieClickListener {
        void onItemClick(View view,int position);
        void onRemoveClicked(int position);
    }


    public LayoutAdapter(Context context){
        mContext = context;
        /*mMovies =  movies;
        mListener = listener;*/
        this.mInflater = LayoutInflater.from(context);
        this.mMovies = new ArrayList<>();
    }

    public void setMovieList(List<Movies> movieList)
    {
        this.mMovies.clear();
        this.mMovies.addAll(movieList);
        // The adapter needs to know that the data has changed. If we don't call this, app will crash.
        notifyDataSetChanged();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleTextView;
        ImageView poster;
        //Bar companyRatingBar;
        MovieClickListener mMovieClickListener;

        public MovieViewHolder(View itemView,MovieClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mMovieClickListener = listener;
            titleTextView = itemView.findViewById(R.id.title);
            poster = itemView.findViewById(R.id.poster);
            //companyRatingBar = itemView.findViewById(R.id.company_rating);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                if(id == R.id.list){
                    mMovieClickListener.onItemClick(view,position);
                }
            }

        }
    }
}
