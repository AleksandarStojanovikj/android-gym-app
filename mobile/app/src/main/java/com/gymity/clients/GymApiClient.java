package com.gymity.clients;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GymApiClient {
    private static Retrofit retrofit;
//    private static final String BASE_URL = "http://192.168.1.135:8080";
//    private static final String BASE_URL = "http://10.0.2.2:8080";
//private static final String BASE_URL = "http://192.168.100.2:8080";
    private static final String BASE_URL = "http://172.17.71.54:8080";

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
