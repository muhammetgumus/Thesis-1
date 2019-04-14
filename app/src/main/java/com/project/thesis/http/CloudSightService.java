package com.project.thesis.http;

import com.project.thesis.model.ImageData;
import com.project.thesis.model.ProductData;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface CloudSightService {

    @Multipart
    @POST("image/text")
    Call<ImageData> processImage(@Part MultipartBody.Part body);


    @GET("/products/{title}")
    Call<List<ProductData>> findProducts(@Path("title") String title);

}
