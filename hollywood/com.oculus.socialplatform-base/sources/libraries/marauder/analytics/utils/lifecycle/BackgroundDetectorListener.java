package libraries.marauder.analytics.utils.lifecycle;

public interface BackgroundDetectorListener {
    void onAppBackgrounded();

    void onAppForegrounded();
}
