package com.example.tkmticketunion.utils;

public class URLUtil {
    public static String getImageUrl(String url) {
        if (url.startsWith("http:") || url.startsWith("https:")) {
            return url;
        }
        return "https:" + url;
    }

    public static String getImageUrl(String url, int width, int height) {
        if (width != -1 && height != -1) {
            url = String.format("%s_%dx%d.jpg", url, width, height);
        }
        return getImageUrl(url);
    }

    public static String getHomeCategoryImageUrl(String url) {
        return getImageUrl(url, 300, 300);
    }
}
