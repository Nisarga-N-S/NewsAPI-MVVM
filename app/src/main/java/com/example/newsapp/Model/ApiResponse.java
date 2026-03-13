package com.example.newsapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("articles")
    List<Article> articles;
    public List<Article> getArticles() {
        return articles;
    }
}
