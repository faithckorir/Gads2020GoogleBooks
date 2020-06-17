package com.finixtore.googlebooksapigads2020;

import android.net.Uri;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class APIUtil {
    private static final String QUERY_PARAMETER_KEY ="q" ;
    private static final String KEY ="key" ;
    private static final String API_KEY ="AIzaSyBovPeEGari6pi8oGj0l52U5XVtcq6K8jY" ;

    private APIUtil() {
    }

    public static final String BASE_API_URI = "https://www.googleapis.com/books/v1/volumes";

    public static URL buildURL(String title) {

        URL url = null;
        Uri uri=Uri.parse(BASE_API_URI).buildUpon()
                .appendQueryParameter(QUERY_PARAMETER_KEY,title)
                .appendQueryParameter(KEY,API_KEY)
                .build();

        try {


            url = new URL(uri.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }
    public static  String getJsonData(URL url) throws IOException {

        //first establish a connection using http connection
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();

        try {
            InputStream stream=connection.getInputStream();
            Scanner scanner=new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasdata=scanner.hasNext();



        if (hasdata){
            return scanner.next();

        }else {
            return null;
        }}catch (Exception e){
            Log.d("Error Message",e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }
    }

    public static ArrayList<Book> getBooksFromJson(String json){

        final  String ID="id";
        final String TITLE="title";
        final String SUBTITLE="subtitle";
        final String AUTHORS="authors";
        final String PUBLISHER="publisher";
        final String PUBLISHERDATE="publishedDate";
        final String ITEMS="items";
        final String VOLUMEINFO="volumeInfo";
        final String DESCRIPTION="description";
        final String IMAGELINKS="imageLinks";
        final String THUMBNAILS="thumbnail";
        ArrayList<Book> bookFromJson =new ArrayList<>();
        try {

            JSONObject jsonBooks=new JSONObject(json);
            JSONArray arrayBooks= jsonBooks.getJSONArray(ITEMS);
            int numOfBooks=arrayBooks.length();
            for(int i=0;i<numOfBooks;i++){
                JSONObject bookJson=arrayBooks.getJSONObject(i);
                JSONObject volumeInfo=bookJson.getJSONObject(VOLUMEINFO);
                JSONObject imageLinksJson=volumeInfo.getJSONObject(IMAGELINKS);
                int numOfAuthors=volumeInfo.getJSONArray(AUTHORS).length();
                String[] authors=new String[numOfAuthors];
                for (int j=0;j<numOfAuthors;j++){
                    authors[j]=volumeInfo.getJSONArray(AUTHORS).get(j).toString();
                }

                Book book=new Book((String) bookJson.get(ID),volumeInfo.getString(TITLE),
                        (volumeInfo.isNull(SUBTITLE)?"":volumeInfo.getString(SUBTITLE)),
                        authors,volumeInfo.getString(PUBLISHER),volumeInfo.getString(PUBLISHERDATE),
                        volumeInfo.getString(DESCRIPTION),imageLinksJson.getString(THUMBNAILS));

                bookFromJson.add(book);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return bookFromJson;
    }
}
