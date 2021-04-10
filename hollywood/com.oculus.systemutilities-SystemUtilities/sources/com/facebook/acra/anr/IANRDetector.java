package com.facebook.acra.anr;

public interface IANRDetector {

    public interface ANRDetectorStopListener {
        void onStop();
    }

    void start();

    void stop(ANRDetectorStopListener aNRDetectorStopListener);
}
