package com.example.MoveOn;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String HOST = "192.168.1.62";
    public static final String PORT = "8080";
    public static final String ORIGIN = "http://" + HOST + ":" + PORT;
    public static final String SITE_URL = ORIGIN + "/move_on/";
    private static final String SITE_ROOT = ORIGIN + "/move_on";
    public static final String API_BASE_URL = SITE_URL + "app/api/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static String resolveUrl(String path) {
        if (path == null || path.trim().isEmpty()) {
            return "";
        }

        String normalizedPath = path.trim().replace("\\", "/");
        if (normalizedPath.startsWith("http://") || normalizedPath.startsWith("https://")) {
            return normalizedPath;
        }

        if (normalizedPath.startsWith("/move_on/")) {
            return ORIGIN + normalizedPath;
        }

        if (normalizedPath.startsWith("move_on/")) {
            return ORIGIN + "/" + normalizedPath;
        }

        if (normalizedPath.startsWith("/")) {
            return SITE_ROOT + normalizedPath;
        }

        return SITE_URL + normalizedPath;
    }
}
