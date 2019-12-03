package mk.ukim.finki.students.moviesomdb.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

import mk.ukim.finki.students.moviesomdb.MoviesInterface;
import mk.ukim.finki.students.moviesomdb.client.OMDbApiClient;
import mk.ukim.finki.students.moviesomdb.db.Repository;
import mk.ukim.finki.students.moviesomdb.models.OMDbMovie;
import mk.ukim.finki.students.moviesomdb.models.OMDbMovies;
import retrofit2.Call;
import retrofit2.Response;

public class OMDbMoviesAsyncTask extends AsyncTask<String, Integer, OMDbMovies> {

    Repository repository;

    public OMDbMoviesAsyncTask(Repository repository) {
        this.repository = repository;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        repository.deleteAll();
    }

    @Override
    protected OMDbMovies doInBackground(String... strings) {
        final Call<OMDbMovies> moviesCall = OMDbApiClient.getService().getMovies(strings[0]);
        try {
            Response<OMDbMovies> s = moviesCall.execute();
            OMDbMovies movie = s.body();
            return movie;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(OMDbMovies omDbMovies) {
        if (omDbMovies.Response.equals("True"))
            for (OMDbMovie movie : omDbMovies.Search) {
                repository.insert(movie);
            }
    }
}
