package mk.ukim.finki.students.moviesomdb.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "omdb_movie")
public class OMDbMovie {
    @ColumnInfo(name = "custom_title")
    public String Title;
    public String Year;
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    public String imdbID;
    public String Type;
    public String Poster;
}
