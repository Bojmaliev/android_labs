package mk.ukim.finki.students.moviesomdb.service;

import mk.ukim.finki.students.moviesomdb.models.OMDbMovies;
import mk.ukim.finki.students.moviesomdb.models.Places;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlacesService {

    @GET("maps/api/place/nearbysearch/json?key=AIzaSyDJYwGEOK3S_F2i-cV9TgOIBOmbwW85wl0&radius=10000&types=bank")
    Call<Places> getPlaces(@Query("location") String query);
}
