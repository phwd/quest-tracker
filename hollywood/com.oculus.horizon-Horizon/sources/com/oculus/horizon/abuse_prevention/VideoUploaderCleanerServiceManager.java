package com.oculus.horizon.abuse_prevention;

import X.AnonymousClass0NO;
import com.oculus.time.Clock;
import java.io.File;
import java.io.IOException;

public class VideoUploaderCleanerServiceManager {
    public static final long STALE_THRESHOLD_MILLIS = 86400000;
    public static final String TAG = "VideoUploaderCleanerServiceManager";
    public Clock mClock = new Clock();

    public static String A00(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            AnonymousClass0NO.A0B(TAG, "error in getCanonicalPath", e);
            return "";
        }
    }
}
