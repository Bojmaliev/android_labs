package mk.ukim.finki.students.moviesomdb.client;

import mk.ukim.finki.students.moviesomdb.service.OMDbMoviesService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  OMDbApiClient {

    private static Retrofit retrofit = null;

    private static Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://omdbapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static OMDbMoviesService getService() {
        return getRetrofit().create(OMDbMoviesService.class);
    }

}