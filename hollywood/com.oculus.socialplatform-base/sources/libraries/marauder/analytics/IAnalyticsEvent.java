package libraries.marauder.analytics;

public interface IAnalyticsEvent {
    IAnalyticsEvent addExtra(String str, double d);

    IAnalyticsEvent addExtra(String str, int i);

    IAnalyticsEvent addExtra(String str, long j);

    IAnalyticsEvent addExtra(String str, String str2);

    IAnalyticsEvent addExtra(String str, boolean z);

    void log();

    IAnalyticsEvent setTime(long j);

    IAnalyticsEvent setUserId(String str);

    String toString();
}
