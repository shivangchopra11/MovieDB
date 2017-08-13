package com.example.shivang.movies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.shivang.movies.Network.APIInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UpcomingMovies extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LayoutAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Upcoming Movies");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.list_upcoming);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mAdapter = new LayoutAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        List<Movies> movies = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            movies.add(new Movies());
        }
        mAdapter.setMovieList(movies);


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key", "35306f818103e6fc4e08a236da4b4a5c");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        APIInterface service = restAdapter.create(APIInterface.class);

        final Calendar c = Calendar.getInstance();
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH) + 1;
        int dd = c.get(Calendar.DAY_OF_MONTH);
        String formattedDate=""+yy+"-"+mm+"-"+dd;


        service.getUpcomingMovies(formattedDate,new Callback<Movies.MovieResult>() {

            @Override
            public void success(Movies.MovieResult movieResult, Response response) {
                mAdapter.setMovieList(movieResult.getResults());
            }

            @Override
            public void failure(RetrofitError error) {

            }
            });

    }

}
