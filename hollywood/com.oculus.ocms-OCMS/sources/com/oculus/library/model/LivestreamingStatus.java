package com.oculus.library.model;

import android.util.Log;

public enum LivestreamingStatus {
    UNKNOWN,
    ACCEPTED,
    REJECTED;
    
    private static final String TAG = LivestreamingStatus.class.getCanonicalName();

    public static LivestreamingStatus fromString(String str) {
        LivestreamingStatus[] values = values();
        for (LivestreamingStatus livestreamingStatus : values) {
            if (livestreamingStatus.name().equals(str)) {
                return livestreamingStatus;
            }
        }
        Log.e(TAG, "Cannot livestreaming status: " + str);
        return null;
    }
}
