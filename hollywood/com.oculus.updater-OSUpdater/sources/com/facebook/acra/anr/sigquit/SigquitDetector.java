package com.facebook.acra.anr.sigquit;

import android.os.Handler;
import com.facebook.acra.anr.ANRDetectorConfig;

public interface SigquitDetector {
    void cleanupAppStateFile();

    void init(ANRDetectorConfig aNRDetectorConfig, boolean z);

    void installSignalHandler(Handler handler, boolean z);

    void startDetector();

    void stopDetector();
}
