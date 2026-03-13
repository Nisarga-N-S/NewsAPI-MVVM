package com.example.newsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.newsapp.ItemClickListener;
import com.example.newsapp.Model.Article;
import com.example.newsapp.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    List<Article>  articles;
    Context context;
   ItemClickListener itemClickListener;

    public NewsAdapter(List<Article> articles,Context context,ItemClickListener itemClickListener) {
        this.articles = articles;
        this.context=context;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Article item = articles.get(position);

        holder.date.setText(item.getPublishedAt().substring(0,10));
        holder.author.setText(item.getAuthor());
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.content.setText(item.getContent());
        holder.publishedname.setText(context.getString( R.string.published_by ) +  item.getSource().getName());
        String url=item.getUrlToImage();
        Glide.with(context)
                .load(url)
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.itemClick(item);

            }
        });
    }
    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView author,title,description,content,publishedname,date;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.date);
            author=itemView.findViewById(R.id.author);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            content=itemView.findViewById(R.id.content);
            publishedname=itemView.findViewById(R.id.published_name);
            image=itemView.findViewById(R.id.shapeableImageView);
        }
    }
}
