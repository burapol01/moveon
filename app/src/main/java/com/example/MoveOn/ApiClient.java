package com.example.MoveOn;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final boolean USE_REMOTE_SERVER = true;
    private static final String REMOTE_ORIGIN = "https://moveon-juap.onrender.com";
    private static final String REMOTE_BASE_PATH = "";
    private static final String LOCAL_ORIGIN = "http://192.168.1.62:8080";
    private static final String LOCAL_BASE_PATH = "/move_on";

    public static final String ORIGIN = USE_REMOTE_SERVER ? REMOTE_ORIGIN : LOCAL_ORIGIN;
    private static final String BASE_PATH = USE_REMOTE_SERVER ? REMOTE_BASE_PATH : LOCAL_BASE_PATH;
    private static final String SITE_ROOT = BASE_PATH.isEmpty() ? ORIGIN : ORIGIN + BASE_PATH;
    public static final String SITE_URL = SITE_ROOT + "/";
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
            normalizedPath = normalizedPath.substring("/move_on".length());
        }

        if (normalizedPath.startsWith("move_on/")) {
            normalizedPath = normalizedPath.substring("move_on".length());
        }

        if (normalizedPath.startsWith("/")) {
            return SITE_ROOT + normalizedPath;
        }

        return SITE_URL + normalizedPath;
    }
}
