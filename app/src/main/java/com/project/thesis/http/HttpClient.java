package com.project.thesis.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    public static Retrofit createClient() {
        return new Retrofit.Builder()
                .baseUrl("http://35.198.131.192:8080")
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildHttpClient())
                .build();
    }

    private static OkHttpClient buildHttpClient() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        return httpClient.build();
    }

}
