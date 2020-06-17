package com.finixtore.googlebooksapigads2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.finixtore.googlebooksapigads2020.databinding.ActivityBookDetailBinding;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Book book= getIntent().getParcelableExtra("Book");
        ActivityBookDetailBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_book_detail);
        binding.setBook(book);
    }
}
