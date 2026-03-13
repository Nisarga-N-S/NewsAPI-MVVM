package com.example.newsapp.View;

import static com.example.newsapp.databinding.ItemNewsBinding.inflate;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.newsapp.Model.Article;
import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityNewsItemBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class NewsItemDisplay extends AppCompatActivity {

    private ActivityNewsItemBinding binding;
    Article article=null;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewsItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        intent=getIntent();
        article=intent.getParcelableExtra("Information");

        binding.toolbar.setTitle(intent.getStringExtra("category_key"));
        binding.date.setText(article.getPublishedAt().substring(0,10));
        binding.author.setText(article.getAuthor());
        binding.title.setText(article.getTitle());
        binding.description.setText(article.getDescription());
        String imagepath=article.getUrlToImage();
        Glide.with(this)
                .load(imagepath)
                .into(binding.shapeableImageView);
        binding.content.setText(article.getContent());
        binding.publishedName.setText(String.format(getString(R.string.published_by)+article.getSource().getName()));
    }
}