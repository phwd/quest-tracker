package com.oculus.alpenglow.logging;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R7;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.os.AnalyticsEvent;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_alpenglow_logging_LoggerImpl_ULSEP_BINDING_ID"})
public class OSUpdateLogger {
    public AnonymousClass0R7 _UL_mInjectionContext;

    @AutoGeneratedFactoryMethod
    public static final OSUpdateLogger A00(AbstractC02990bJ r1) {
        return new OSUpdateLogger(r1);
    }

    public final void A01(boolean z, boolean z2, @Nullable String str) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(LoggerConstants.EVENT_OS_UPDATE_CONFIGURED);
        if (str == null) {
            str = "null";
        }
        ((LoggerImpl) AnonymousClass0Lh.A03(0, 121, this._UL_mInjectionContext)).A01(analyticsEvent.setExtra(LoggerConstants.ERROR_KEY, str).setExtra(LoggerConstants.ENABLED_KEY, Integer.valueOf(z ? 1 : 0)).setExtra(LoggerConstants.SCHEDULED_KEY, Integer.valueOf(z2 ? 1 : 0)), true);
    }

    @Inject
    public OSUpdateLogger(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(1, r3);
    }
}
