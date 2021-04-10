package libraries.marauder.analytics;

public class Analytics {
    public static AnalyticsLogger sLogger;

    public static AnalyticsLogger getLogger() {
        return sLogger;
    }

    public static void setLogger(AnalyticsLogger analyticsLogger) {
        sLogger = analyticsLogger;
    }
}
