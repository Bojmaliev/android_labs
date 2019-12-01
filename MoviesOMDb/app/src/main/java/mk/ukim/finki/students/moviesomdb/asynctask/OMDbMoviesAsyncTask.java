package mk.ukim.finki.students.moviesomdb.asynctask;

import android.os.AsyncTask;

import java.io.IOException;

import mk.ukim.finki.students.moviesomdb.MoviesInterface;
import mk.ukim.finki.students.moviesomdb.client.OMDbApiClient;
import mk.ukim.finki.students.moviesomdb.models.OMDbMovie;
import mk.ukim.finki.students.moviesomdb.models.OMDbMovies;
import retrofit2.Call;

public class OMDbMoviesAsyncTask extends AsyncTask<String, Integer, OMDbMovies> {
    private MoviesInterface moviesInterface;

    public OMDbMoviesAsyncTask(MoviesInterface moviesInterface) {
        this.moviesInterface = moviesInterface;
    }


    @Override
    protected OMDbMovies doInBackground(String... strings) {
        final Call<OMDbMovies> moviesCall = OMDbApiClient.getService().getMovies(strings[0]);
        try{
            return moviesCall.execute().body();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(OMDbMovies omDbMovies) {
        moviesInterface.loadedOMDbMovies(omDbMovies);
    }
}
