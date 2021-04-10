package com.oculus.library.model;

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
}
