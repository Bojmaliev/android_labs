package mk.ukim.finki.students.moviesomdb.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import mk.ukim.finki.students.moviesomdb.models.OMDbMovie;

public class Repository {
    private static OMDbDatabase database = null;

    public Repository(Context context) {
        if(database == null) {
            database = Room
                    .databaseBuilder(context, OMDbDatabase.class, "db-app")
                    .build();
        }
//        fetchData();
    }
    public LiveData<List<OMDbMovie>> getAllMovies() {
        return database.getOMDbMovieDao().getAllAsync();
    }

    public LiveData<OMDbMovie> getMovieById(String id){
        return database.getOMDbMovieDao().findById(id);
    }

    public void insert(OMDbMovie movie) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.getOMDbMovieDao().insert(movie);
                return null;
            }
        }.execute();
    }

    public void deleteAll() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.getOMDbMovieDao().deleteAll();
                return null;
            }
        }.execute();

    }
}
