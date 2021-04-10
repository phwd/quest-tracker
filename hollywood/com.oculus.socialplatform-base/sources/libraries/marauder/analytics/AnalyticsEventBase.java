package libraries.marauder.analytics;

import libraries.debug.log.BLog;
import org.json.JSONException;
import org.json.JSONObject;

public class AnalyticsEventBase implements IAnalyticsEvent {
    public static final String EXTRAS_KEY = "extra";
    public static final String NAME_KEY = "name";
    public static final String TAG = "FlatAnalyticsEvent";
    public static final String TIME_KEY = "time";
    public JSONObject mExtras = new JSONObject();
    public final JSONObject mPayload = new JSONObject();

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public void log() {
        Analytics.sLogger.reportEvent(this);
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public AnalyticsEventBase addExtra(String str, double d) {
        try {
            this.mExtras.put(str, d);
            return this;
        } catch (JSONException e) {
            BLog.e(TAG, "", e);
            return this;
        }
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public AnalyticsEventBase addExtra(String str, int i) {
        try {
            this.mExtras.put(str, i);
            return this;
        } catch (JSONException e) {
            BLog.e(TAG, "", e);
            return this;
        }
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public AnalyticsEventBase addExtra(String str, long j) {
        try {
            this.mExtras.put(str, j);
            return this;
        } catch (JSONException e) {
            BLog.e(TAG, "", e);
            return this;
        }
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public AnalyticsEventBase addExtra(String str, String str2) {
        try {
            this.mExtras.put(str, str2);
            return this;
        } catch (JSONException e) {
            BLog.e(TAG, "", e);
            return this;
        }
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public AnalyticsEventBase addExtra(String str, boolean z) {
        try {
            this.mExtras.put(str, z);
            return this;
        } catch (JSONException e) {
            BLog.e(TAG, "", e);
            return this;
        }
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public AnalyticsEventBase setTime(long j) {
        try {
            this.mPayload.put(TIME_KEY, AnalyticsUtil.formatServerTime(j));
            return this;
        } catch (JSONException e) {
            BLog.e(TAG, "", e);
            return this;
        }
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public AnalyticsEventBase setUserId(String str) {
        addExtra("pk", str);
        return this;
    }
}
