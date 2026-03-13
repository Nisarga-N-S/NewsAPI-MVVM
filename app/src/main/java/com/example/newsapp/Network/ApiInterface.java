package com.example.newsapp.Network;


import com.example.newsapp.Model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines/?country=us&apiKey=8f597e80a947480094ca54a1c1db137e")
    Call<ApiResponse> getdata(
            @Query("category") String category
    );

}
