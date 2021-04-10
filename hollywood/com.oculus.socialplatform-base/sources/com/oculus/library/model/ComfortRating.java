package com.oculus.library.model;

import X.AnonymousClass006;
import android.util.Log;

public enum ComfortRating {
    UNKNOWN(-1),
    COMFORTABLE_FOR_ALL(0),
    COMFORTABLE_FOR_MOST(1),
    COMFORTABLE_FOR_SOME(2),
    COMFORTABLE_FOR_FEW(3),
    NOT_RATED(4);
    
    public static final String TAG = ComfortRating.class.getCanonicalName();
    public final int value;

    /* access modifiers changed from: public */
    ComfortRating(int i) {
        this.value = i;
    }

    public static ComfortRating fromString(String str) {
        ComfortRating[] values = values();
        for (ComfortRating comfortRating : values) {
            if (comfortRating.name().equals(str)) {
                return comfortRating;
            }
        }
        Log.e(TAG, AnonymousClass006.A07("Cannot parse comfort rating: ", str));
        return null;
    }
}
