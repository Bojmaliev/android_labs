package mk.ukim.finki.students.moviesomdb.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import mk.ukim.finki.students.moviesomdb.asynctask.OMDbMoviesAsyncTask;
import mk.ukim.finki.students.moviesomdb.db.Repository;
import mk.ukim.finki.students.moviesomdb.models.OMDbMovie;

public class MoviesViewModel extends AndroidViewModel {

    Repository repository;

    public MoviesViewModel(Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<OMDbMovie>> getAll() {
        return repository.getAllMovies();
    }

    public LiveData<OMDbMovie> getById(String id) {return repository.getMovieById(id);}

    public void fetchData(String query) {
        OMDbMoviesAsyncTask asyncTask = new OMDbMoviesAsyncTask(repository);
        asyncTask.execute(query);
    }

}
