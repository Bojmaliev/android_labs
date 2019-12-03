package mk.ukim.finki.students.moviesomdb.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import mk.ukim.finki.students.moviesomdb.models.OMDbMovie;

@Database(entities = {OMDbMovie.class}, version = 1)
public abstract class OMDbDatabase extends RoomDatabase {

    public abstract OMDbMovieDao getOMDbMovieDao();

}
