package libraries.marauder.analytics;

import libraries.marauder.analytics.utils.json.JsonMap;

public class AnalyticsEvent implements IAnalyticsEvent {
    public final JsonMap mExtras;
    public String mModuleName;
    public final String mName;
    public long mTime;

    public JsonMap getExtras() {
        return this.mExtras;
    }

    public String getModuleName() {
        return this.mModuleName;
    }

    public String getName() {
        return this.mName;
    }

    public long getTime() {
        return this.mTime;
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public void log() {
        Analytics.sLogger.reportEvent(this);
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public String toString() {
        StringBuilder sb = new StringBuilder("{\"name\":\"");
        sb.append(this.mName);
        sb.append("\",\"module\":\"");
        sb.append(this.mModuleName);
        sb.append("\",\"extras\":");
        sb.append(this.mExtras);
        sb.append(",\"time\":");
        sb.append(this.mTime);
        sb.append("}");
        return sb.toString();
    }

    public AnalyticsEvent(String str) {
        this.mExtras = new JsonMap();
        this.mTime = System.currentTimeMillis();
        this.mName = str;
    }

    public AnalyticsEvent(String str, String str2) {
        this(str);
        this.mModuleName = str2;
    }

    public AnalyticsEvent(String str, AnalyticsModule analyticsModule) {
        this(str);
        if (analyticsModule != null) {
            this.mModuleName = analyticsModule.getModuleName();
        }
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public AnalyticsEvent addExtra(String str, double d) {
        this.mExtras.addEntry(str, d);
        return this;
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public AnalyticsEvent addExtra(String str, int i) {
        this.mExtras.addEntry(str, (long) i);
        return this;
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public AnalyticsEvent addExtra(String str, long j) {
        this.mExtras.addEntry(str, j);
        return this;
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public AnalyticsEvent addExtra(String str, String str2) {
        this.mExtras.addEntry(str, str2);
        return this;
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public AnalyticsEvent addExtra(String str, boolean z) {
        this.mExtras.addEntry(str, z);
        return this;
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public AnalyticsEvent setTime(long j) {
        this.mTime = j;
        return this;
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public AnalyticsEvent setUserId(String str) {
        this.mExtras.addEntry("pk", str);
        return this;
    }
}
