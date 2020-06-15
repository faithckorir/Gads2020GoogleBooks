package com.finixtore.googlebooksapigads2020;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.OpenOption;
import java.util.Scanner;

public class APIUtil {
    private APIUtil() {
    }

    public static final String BASE_API_URI = "https://www.googleapis.com/books/v1/volumes";

    public static URL buildURL(String title) {
        String fullUrl = BASE_API_URI + "?q=" + title;
        URL url = null;

        try {


            url = new URL(fullUrl);
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
