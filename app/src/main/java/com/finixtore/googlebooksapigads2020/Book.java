package com.finixtore.googlebooksapigads2020;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class Book implements Parcelable {
public  String id;
public  String title;
public  String subtitle;
public  String authors;
public  String publisher;
public  String publishedDate;
public  String description;

    public Book(String id, String title, String subtitle, String[] authors, String publisher, String publishedDate,String description) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.authors = TextUtils.join(",",authors);
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description=description;
    }


    protected Book(Parcel in) {
        id = in.readString();
        title = in.readString();
        subtitle = in.readString();
        authors = in.readString();
        publisher = in.readString();
        publishedDate = in.readString();
        description = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(authors);
        dest.writeString(publisher);
        dest.writeString(publishedDate);
        dest.writeString(description);
    }
}
