package com.facebook.acra.anr;

public interface IANRDetector {

    public interface ANRDetectorStopListener {
        void onStop();
    }

    void nativeLibraryLoaded(boolean z);

    void pause();

    void resume();

    void setANRReportProvider(ANRReportProvider aNRReportProvider);

    void setCheckIntervalMs(long j);

    void setListener(ANRDetectorListener aNRDetectorListener);

    void start();

    void stop(ANRDetectorStopListener aNRDetectorStopListener);
}
