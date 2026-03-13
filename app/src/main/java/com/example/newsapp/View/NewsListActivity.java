package com.example.newsapp.View;

import static android.view.View.GONE;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newsapp.Adapter.NewsAdapter;
import com.example.newsapp.ItemClickListener;
import com.example.newsapp.Model.Article;
import com.example.newsapp.R;
import com.example.newsapp.ViewModel.NewsViewModel;
import com.example.newsapp.databinding.ActivityNewListBinding;
import java.util.ArrayList;
import java.util.List;

public class NewsListActivity extends AppCompatActivity implements ItemClickListener {

    List<Article> newsList;
    NewsAdapter adapter;
    String category;
    ActivityNewListBinding binding;
    NewsViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityNewListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));

        newsList = new ArrayList<>();

        adapter = new NewsAdapter(newsList, this, this);
        binding.recyclerview.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayAdapter<CharSequence> dropadapter = ArrayAdapter.createFromResource(this, R.array.category_names, android.R.layout.simple_dropdown_item_1line);

        binding.autoCompleteTextView.setAdapter(dropadapter);

        binding.autoCompleteTextView.setText(dropadapter.getItem(0), false);
        category = dropadapter.getItem(0).toString();
        selection(category);


        binding.autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            category = parent.getItemAtPosition(position).toString();
            selection(category);
        });
    }

    private void selection(String category){
        viewModel.fetchNews(category).observe(this, articles -> {
            binding.progressBar.setVisibility(GONE);
            newsList.clear();
            newsList.addAll(articles);
            adapter.notifyDataSetChanged();
        });
}
    @Override
    public void itemClick(Article article) {
        Intent intent=new Intent(NewsListActivity.this, NewsItemDisplay.class);
        intent.putExtra("Information", article);
        intent.putExtra("category_key",category);
        startActivity(intent);
    }
}






