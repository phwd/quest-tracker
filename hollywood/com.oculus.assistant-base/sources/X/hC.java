package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.flexiblesampling.SamplingResult;
import com.facebook.xanalytics.XAnalyticsAdapter;

public final class hC implements XAnalyticsAdapter {
    public final /* synthetic */ hD A00;

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

    public hC(hD hDVar) {
        this.A00 = hDVar;
    }

    @Override // com.facebook.xanalytics.XAnalyticsAdapter
    public final void logEvent(String str, String str2, String str3, boolean z, double d) {
        hD hDVar = this.A00;
        yZ yZVar = hDVar.A00;
        if (yZVar != null) {
            C00446t A01 = yZVar.A02.A01(str);
            if (A01.A05()) {
                A01.A02 = AnonymousClass75.LOGGED_THROUGH_XPLAT.getTag() | A01.A02;
                if (str2 != null) {
                    hD.A00(str, str2, A01);
                    hDVar.A00.A02(A01);
                }
            }
        }
    }

    @Override // com.facebook.xanalytics.XAnalyticsAdapter
    public final void logEventBypassSampling(String str, String str2) {
        hD hDVar = this.A00;
        yZ yZVar = hDVar.A00;
        if (yZVar != null) {
            C00446t A01 = yZVar.A02.A01(str);
            if (A01.A05()) {
                A01.A02 = AnonymousClass75.LOGGED_THROUGH_XPLAT.getTag() | A01.A02;
                if (str2 != null) {
                    hD.A00(str, str2, A01);
                    hDVar.A00.A02(A01);
                }
            }
        }
    }

    @Override // com.facebook.xanalytics.XAnalyticsAdapter
    public final boolean shouldLog(String str) {
        yZ yZVar = this.A00.A00;
        boolean z = false;
        if (yZVar != null) {
            int i = yZVar.A02.A0A.A00().A00;
            z = true;
            if (i == 0 || SamplingResult.A03.nextInt(i) != 0) {
                return false;
            }
        }
        return z;
    }
}
