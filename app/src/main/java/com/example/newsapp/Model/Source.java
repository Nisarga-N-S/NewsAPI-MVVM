package com.example.newsapp.Model;

import android.os.Parcel;
import android.os.Parcelable;


import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Source implements Parcelable{


    @SerializedName("name")
    private String name;

    protected Source(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Source> CREATOR = new Creator<Source>() {
        @Override
        public Source createFromParcel(Parcel in) {
            return new Source(in);
        }

        @Override
        public Source[] newArray(int size) {
            return new Source[size];
        }
    };

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
    }
}
