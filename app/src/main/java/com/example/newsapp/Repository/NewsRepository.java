package com.example.newsapp.Repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.Model.ApiResponse;
import com.example.newsapp.Model.Article;
import com.example.newsapp.Network.ApiInterface;
import com.example.newsapp.Network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    ApiInterface apiInterface;

    public NewsRepository() {
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
    }

    public LiveData<List<Article>> fetchNews(String category) {


        MutableLiveData<List<Article>> newList = new MutableLiveData<>();

        Call<ApiResponse> call = apiInterface.getdata(category);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "onResponse: " + "receiving response properly");
                    newList.postValue(response.body().getArticles());

                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Apiresponse", "onFailure: " + t.getMessage());

            }
        });
        return newList;
    }
}
