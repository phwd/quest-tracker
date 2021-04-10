package com.oculus.fitnesstracker.provider;

import android.net.Uri;

public class FitnessDataContract {
    public static final String AUTHORITY = "com.oculus.fitnesstracker.fitnessdata";
    public static final int INVALID_ERROR_CODE = -1;
    public static final String MATCHER_PATH_CALORIES_DAILY_SUMMARY = "calories-daily-summary";
    public static final String MATCHER_PATH_EFFORT = "effort";
    public static final String PACKAGE_NAME = "com.oculus.fitnesstracker";
    public static final String PATH_CALORIES_DAILY_SUMMARY = "calories-daily-summary";
    public static final String PATH_EFFORT = "effort";
    public static final String PATH_SHUTDOWN = "shutdown";
    private static final String SCHEME_NAME = "content";
    public static final int USER_ID_SELF = -1;

    public static Uri providerUri() {
        return new Uri.Builder().scheme(SCHEME_NAME).encodedAuthority(AUTHORITY).build();
    }

    public static Uri uriForCaloriesDailySummary() {
        return new Uri.Builder().scheme(SCHEME_NAME).encodedAuthority(AUTHORITY).path("calories-daily-summary").build();
    }

    public static Uri uriForEffort() {
        return uriForEffort(-1);
    }

    public static Uri uriForEffort(int i) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEME_NAME);
        if (i > 0) {
            builder.encodedAuthority(String.valueOf(i) + "@com.oculus.fitnesstracker.fitnessdata");
        } else {
            builder.encodedAuthority(AUTHORITY);
        }
        return builder.path("effort").build();
    }

    public static Uri uriForShutdown() {
        return new Uri.Builder().scheme(SCHEME_NAME).encodedAuthority(AUTHORITY).path(PATH_SHUTDOWN).build();
    }
}
