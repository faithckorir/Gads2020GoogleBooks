package com.finixtore.googlebooksapigads2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class BooksListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
private RecyclerView mRecyclerView;
private TextView mTvError;
private ProgressBar mProgressBar;
private BooksAdapter mBooksAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookslist);
        mProgressBar=findViewById(R.id.pb);
        mRecyclerView=findViewById(R.id.rv_books);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mTvError=findViewById(R.id.textView_error);
        URL url=APIUtil.buildURL("cooking");

        new BooksQueryTask().execute(url);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        try {
            URL queryUrl=APIUtil.buildURL(query);
            new BooksQueryTask().execute(queryUrl);

        }catch (Exception e){
            Log.d( "onQueryTextSubmit: ",e.getMessage());
        }
        return  false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    public  class BooksQueryTask extends AsyncTask<URL,Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);;
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl=urls[0];
            String result=null;

            try {
                result=APIUtil.getJsonData(searchUrl);

            } catch (Exception e) {
                Log.e("Error in doInbackground",e.getMessage());
            }
            return  result;
        }


        @Override
        protected void onPostExecute(String s) {
            mProgressBar.setVisibility(View.GONE);
            if (s==null){
                mRecyclerView.setVisibility(View.INVISIBLE);
                mTvError.setVisibility(View.VISIBLE);
            }else{
                mRecyclerView.setVisibility(View.VISIBLE);
                mTvError.setVisibility(
                        View.INVISIBLE

                );
           // mTextView.setText(s);
            }

            ArrayList<Book> books=APIUtil.getBooksFromJson(s);
            /*String[] a=new String[]{"a1","a2"};
            Book book1=new Book("1","fay","cook",new String[]{"a1","a2"},"korir","12.12.2019");
                books.add(book1);*/
            String resultString="";


            Log.d( "onPostExecute: ",resultString);
            mBooksAdapter=new BooksAdapter(books);
            mRecyclerView.setAdapter(mBooksAdapter);

            Log.d( "onPostExecute: ",String.valueOf(books.size()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.books_menu,menu);
        final MenuItem searchItem=menu.findItem(R.id.action_search);
        final SearchView searchView= (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.advanced_search:

                Intent intent= new Intent(this,SearchActivity.class);
                startActivity(intent);
                return  true;
                default:
                    return super.onOptionsItemSelected(item);
        }



    }
}
