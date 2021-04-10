package com.oculus.perflogs.impl.fbquicklog;

import X.AnonymousClass1iI;
import X.AnonymousClass1n9;
import android.annotation.TargetApi;
import android.os.PersistableBundle;
import com.oculus.os.UnifiedTelemetryLogger;

public class UTLHoneyClientLoggerImpl implements AnonymousClass1n9 {
    public static final String PERF_EVENT_NAME = "perf_utl";
    public static final String PERF_JSON_PAYLOAD_FIELD_NAME = "perf_payload_json";
    public static final String TAG = "UTLHoneyClientLoggerImpl";
    public final AnalyticsEventBuilder mAnalyticsEventBuilder;
    public final AnonymousClass1iI mEventBuilderAdapter;
    public final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    @TargetApi(21)
    public static class AnalyticsEventBuilder {
        public String mEventName;
        public final PersistableBundle mExtras = new PersistableBundle();
    }
}
