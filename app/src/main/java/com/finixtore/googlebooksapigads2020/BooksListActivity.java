package com.finixtore.googlebooksapigads2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class BooksListActivity extends AppCompatActivity {
private TextView mTextView;
private TextView mTvError;
private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookslist);
        mProgressBar=findViewById(R.id.pb);
        mTextView=findViewById(R.id.tv_response);
        mTvError=findViewById(R.id.textView_error);
        URL url=APIUtil.buildURL("cooking");
        new BooksQueryTask().execute(url);

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
                mTextView.setVisibility(View.INVISIBLE);
                mTvError.setVisibility(View.VISIBLE);
            }else{
                mTvError.setVisibility(
                        View.INVISIBLE
                );
            mTextView.setText(s);}

            ArrayList<Book> books=APIUtil.getBooksFromJson(s);

            String resultString="";
            for(Book book:books){
                resultString=resultString+book.title+"\n"+book.publishedDate +"\n\n";

            }

            Log.d( "onPostExecute: ",resultString);
            mTextView.setText(resultString);

        }
    }
}
