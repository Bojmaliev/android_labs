package mk.ukim.finki.students.moviesomdb.client;

import mk.ukim.finki.students.moviesomdb.service.OMDbMoviesService;
import mk.ukim.finki.students.moviesomdb.service.PlacesService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlacesClient {

    private static Retrofit retrofit = null;

    private static Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static PlacesService getService() {
        return getRetrofit().create(PlacesService.class);
    }

}