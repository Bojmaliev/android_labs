package mk.ukim.finki.students.moviesomdb.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mk.ukim.finki.students.moviesomdb.models.OMDbMovie;

@Dao
public interface OMDbMovieDao {

    @Query("SELECT * from omdb_movie")
    public List<OMDbMovie> getAll();

    @Query("SELECT * from omdb_movie")
    public LiveData<List<OMDbMovie>> getAllAsync();

    @Query("SELECT * from omdb_movie WHERE custom_title LIKE :title")
    public List<OMDbMovie> findByTitle(String title); // title == "abc"

    @Query("SELECT * from omdb_movie WHERE id = :id")
    LiveData<OMDbMovie> findById(String id);

    @Insert
    public void insert(OMDbMovie ...tracks);

    @Query("DELETE from omdb_movie WHERE id = :id")
    public void delete(Long id);

    @Query("DELETE from omdb_movie")
    public void deleteAll();

}
