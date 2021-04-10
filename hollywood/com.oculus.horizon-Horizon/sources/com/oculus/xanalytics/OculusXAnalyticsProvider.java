package com.oculus.xanalytics;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0il;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.xanalytics.XAnalyticsAdapter;
import com.facebook.xanalytics.XAnalyticsAdapterHolder;
import com.facebook.xanalytics.XAnalyticsHolder;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class OculusXAnalyticsProvider implements AnonymousClass0il {
    public AnonymousClass0QC _UL_mInjectionContext;
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
            ((EventManager) AnonymousClass0J2.A03(0, 242, OculusXAnalyticsProvider.this._UL_mInjectionContext)).A23(str, str2, z).A5L();
        }

        @Override // com.facebook.xanalytics.XAnalyticsAdapter
        public final void logEventBypassSampling(String str, String str2) {
            Event A23 = ((EventManager) AnonymousClass0J2.A03(0, 242, OculusXAnalyticsProvider.this._UL_mInjectionContext)).A23(str, str2, false);
            A23.A8v();
            A23.A5L();
        }
    });

    @Inject
    public OculusXAnalyticsProvider(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }

    @Override // X.AnonymousClass0il
    public final XAnalyticsHolder A4h() {
        return this.mXAnalyticsAdapter;
    }
}
