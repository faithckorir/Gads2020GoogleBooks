package com.finixtore.googlebooksapigads2020;

import java.net.URL;

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
}
