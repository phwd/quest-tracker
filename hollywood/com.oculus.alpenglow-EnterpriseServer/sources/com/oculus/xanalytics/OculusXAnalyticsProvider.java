package com.oculus.xanalytics;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R7;
import X.AnonymousClass0kj;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.xanalytics.XAnalyticsAdapter;
import com.facebook.xanalytics.XAnalyticsAdapterHolder;
import com.facebook.xanalytics.XAnalyticsHolder;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class OculusXAnalyticsProvider implements AnonymousClass0kj {
    public AnonymousClass0R7 _UL_mInjectionContext;
    public final XAnalyticsAdapterHolder mXAnalyticsAdapter = new XAnalyticsAdapterHolder(new XAnalyticsAdapter() {
        /* class com.oculus.xanalytics.OculusXAnalyticsProvider.AnonymousClass1 */

        @Override // com.facebook.xanalytics.XAnalyticsAdapter
        public final void cleanup() {
        }

        @Override // com.facebook.xanalytics.XAnalyticsAdapter
        public final void flush() {
        }

        @Override // com.facebook.xanalytics.XAnalyticsAdapter
        public final String getStructureSamplingConfig(String str) {
            return "";
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

        @Override // com.facebook.xanalytics.XAnalyticsAdapter
        public final void logEvent(String str, String str2, String str3, boolean z, double d) {
            ((EventManager) AnonymousClass0Lh.A03(0, 103, OculusXAnalyticsProvider.this._UL_mInjectionContext)).A20(str, str2, z).A5i();
        }

        @Override // com.facebook.xanalytics.XAnalyticsAdapter
        public final void logEventBypassSampling(String str, String str2) {
            Event A20 = ((EventManager) AnonymousClass0Lh.A03(0, 103, OculusXAnalyticsProvider.this._UL_mInjectionContext)).A20(str, str2, false);
            A20.A8I();
            A20.A5i();
        }
    });

    @Inject
    public OculusXAnalyticsProvider(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(1, r3);
    }

    @Override // X.AnonymousClass0kj
    public final XAnalyticsHolder A4z() {
        return this.mXAnalyticsAdapter;
    }
}
