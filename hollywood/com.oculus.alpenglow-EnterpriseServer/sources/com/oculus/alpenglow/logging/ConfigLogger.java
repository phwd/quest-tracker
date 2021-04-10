package com.oculus.alpenglow.logging;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R7;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.os.AnalyticsEvent;
import java.util.UUID;

@Dependencies({"_UL__ULSEP_com_oculus_alpenglow_logging_LoggerImpl_ULSEP_BINDING_ID"})
public class ConfigLogger {
    public AnonymousClass0R7 _UL_mInjectionContext;

    @AutoGeneratedFactoryMethod
    public static final ConfigLogger A00(AbstractC02990bJ r1) {
        return new ConfigLogger(r1);
    }

    public final String A01(String str, String str2) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(LoggerConstants.EVENT_CONFIGURATION_FETCH_STARTED);
        String uuid = UUID.randomUUID().toString();
        analyticsEvent.setExtra("uuid", uuid);
        analyticsEvent.setExtra(LoggerConstants.REASON, str);
        analyticsEvent.setExtra(LoggerConstants.CONFIGURATION_FETCH_TYPE, str2);
        ((LoggerImpl) AnonymousClass0Lh.A03(0, 121, this._UL_mInjectionContext)).A01(analyticsEvent, false);
        return uuid;
    }

    public final void A02(String str, int i) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(LoggerConstants.EVENT_CONFIGURATION_FETCH_RETRY);
        if (str == null) {
            str = UUID.randomUUID().toString();
        }
        analyticsEvent.setExtra("uuid", str);
        analyticsEvent.setExtra(LoggerConstants.CONFIGURATION_FETCH_RETRY_COUNT, Integer.valueOf(i));
        ((LoggerImpl) AnonymousClass0Lh.A03(0, 121, this._UL_mInjectionContext)).A01(analyticsEvent, false);
    }

    public final void A03(String str, boolean z) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(LoggerConstants.EVENT_CONFIGURATION_FETCH_COMPLETED);
        if (str == null) {
            str = UUID.randomUUID().toString();
        }
        analyticsEvent.setExtra("uuid", str);
        analyticsEvent.setExtra(LoggerConstants.CONFIGURATION_FETCH_SUCCESS, Boolean.valueOf(z));
        ((LoggerImpl) AnonymousClass0Lh.A03(0, 121, this._UL_mInjectionContext)).A01(analyticsEvent, false);
    }

    @Inject
    public ConfigLogger(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(1, r3);
    }
}
