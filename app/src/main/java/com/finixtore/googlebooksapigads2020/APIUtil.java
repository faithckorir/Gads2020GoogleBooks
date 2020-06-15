package com.finixtore.googlebooksapigads2020;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.OpenOption;
import java.security.Key;
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
}
