package com.finixtore.googlebooksapigads2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class BooksListActivity extends AppCompatActivity {
private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookslist);
        mTextView=findViewById(R.id.tv_response);
        URL url=APIUtil.buildURL("cooking");
        new BooksQueryTask().execute(url);

    }


    public  class BooksQueryTask extends AsyncTask<URL,Void,String>{


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
            mTextView.setText(s);

        }
    }
}
