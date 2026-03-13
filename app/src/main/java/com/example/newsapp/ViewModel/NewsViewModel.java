package com.example.newsapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.Model.Article;
import com.example.newsapp.Repository.NewsRepository;

import java.util.List;

public class NewsViewModel extends ViewModel {

    NewsRepository repository;

    private MutableLiveData<List<Article>> newList=new MutableLiveData<>();

    public NewsViewModel() {
        repository=new NewsRepository();
    }
    public LiveData<List<Article>> fetchNews(String category){
        return repository.fetchNews(category);
    }
}
