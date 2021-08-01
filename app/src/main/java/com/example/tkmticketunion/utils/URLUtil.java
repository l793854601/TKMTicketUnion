package com.example.tkmticketunion.utils;

public class URLUtil {
    public static String getImageUrl(String url) {
        if (url.startsWith("http:") || url.startsWith("https:")) {
            return url;
        }
        return "https:" + url;
    }
}
