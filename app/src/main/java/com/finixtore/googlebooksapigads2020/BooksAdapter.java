package com.finixtore.googlebooksapigads2020;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {


    ArrayList<Book> mBooks;

    public  BooksAdapter(ArrayList<Book> books) {
        this.mBooks=books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item_layout,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book=mBooks.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }
//1st create a viewholder class.
    //Make sure you add a recyclerview dependency first in the
// app gradle file e.g  implementation 'androidx.recyclerview:recyclerview:1.1.0'

    //the viewholder holds the single item layout for the recyclerview
    public class BookViewHolder extends RecyclerView.ViewHolder {
        final TextView mTitle;
        TextView mPublishers;
        TextView mDate;
        TextView mAuthors;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_title);
            mPublishers = itemView.findViewById(R.id.tv_publisher);
            mDate = itemView.findViewById(R.id.tv_publisherDate);
            mAuthors = itemView.findViewById(R.id.tv_authors);
        }

        public void bind(Book book) {
            mTitle.setText(book.title);
            String authors="";
            int i=0;
            for (String author:book.authors){
                authors+=author;
                i++;
                if (i<book.authors.length){
                    authors+=", ";

                }
            }
            mAuthors.setText(authors);
            mPublishers.setText(book.publisher);
            mDate.setText(book.publishedDate);


        }


    }
}
