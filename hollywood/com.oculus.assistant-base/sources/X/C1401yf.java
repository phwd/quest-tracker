package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.xanalytics.XAnalyticsAdapter;
import com.oculus.os.AnalyticsEvent;

/* renamed from: X.yf  reason: case insensitive filesystem */
public final class C1401yf implements XAnalyticsAdapter {
    public final /* synthetic */ C1402yg A00;

    @Override // com.facebook.xanalytics.XAnalyticsAdapter
    public final void cleanup() {
    }

    @Override // com.facebook.xanalytics.XAnalyticsAdapter
    public final void flush() {
    }

    @Override // com.facebook.xanalytics.XAnalyticsAdapter
    public final String getStructureSamplingConfig(String str) {
        return OacrConstants.AUTO_SPEECH_DOMAIN;
    }

    @Override // com.facebook.xanalytics.XAnalyticsAdapter
    public final void logCounter(String str, double d) {
    }

    @Override // com.facebook.xanalytics.XAnalyticsAdapter
    public final void logCounter(String str, double d, String str2) {
    }

    @Override // com.facebook.xanalytics.XAnalyticsAdapter
    public final boolean shouldLog(String str) {
        return true;
    }

    public C1401yf(C1402yg ygVar) {
        this.A00 = ygVar;
    }

    @Override // com.facebook.xanalytics.XAnalyticsAdapter
    public final void logEvent(String str, String str2, String str3, boolean z, double d) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(str);
        analyticsEvent.setXplatTag();
        if (str2 != null) {
            C1402yg.A00(str, str2, analyticsEvent);
        }
        yZ.A00().reportEvent(analyticsEvent, false);
    }

    @Override // com.facebook.xanalytics.XAnalyticsAdapter
    public final void logEventBypassSampling(String str, String str2) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(str);
        analyticsEvent.setXplatTag();
        if (str2 != null) {
            C1402yg.A00(str, str2, analyticsEvent);
        }
        yZ.A00().reportEvent(analyticsEvent, false);
    }
}
