package com.oculus.library.model;

import android.util.Log;

public enum ComfortRating {
    UNKNOWN(-1),
    COMFORTABLE_FOR_ALL(0),
    COMFORTABLE_FOR_MOST(1),
    COMFORTABLE_FOR_SOME(2),
    COMFORTABLE_FOR_FEW(3),
    NOT_RATED(4);
    
    private static final String TAG = ComfortRating.class.getCanonicalName();
    public final int value;

    private ComfortRating(int value2) {
        this.value = value2;
    }

    public static ComfortRating fromString(String value2) {
        ComfortRating[] values = values();
        for (ComfortRating rating : values) {
            if (rating.name().equals(value2)) {
                return rating;
            }
        }
        Log.e(TAG, "Cannot parse comfort rating: " + value2);
        return null;
    }
}
