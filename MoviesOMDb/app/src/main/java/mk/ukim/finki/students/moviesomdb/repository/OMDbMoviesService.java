package mk.ukim.finki.students.moviesomdb.repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import mk.ukim.finki.students.moviesomdb.models.OMDbMovies;

public interface OMDbMoviesService {

    @GET("&s={query}&type=movie")
    Call<OMDbMovies> getMovies(@Path("query") String query);
}
