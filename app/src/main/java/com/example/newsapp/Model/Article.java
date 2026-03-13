package com.example.newsapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Article implements Parcelable{

    @SerializedName("source")
    private Source source;

    @SerializedName("author")
    private String author;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("url")
    private String url;

    @SerializedName("urlToImage")
    private String urlToImage;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("content")
    private String content;

    public Source getSource(){
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(publishedAt);
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(urlToImage);
        dest.writeString(content);
        dest.writeParcelable((Parcelable) source,flags);


    }

    public static final Creator<Article> CREATOR= new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    protected Article(Parcel in){
        publishedAt=in.readString();
        author=in.readString();
        title=in.readString();
        description=in.readString();
        urlToImage=in.readString();
        content=in.readString();
        source=in.readParcelable(Source.class.getClassLoader());

    }
}
