package com.oculus.perflogs.impl.fbquicklog;

import X.AnonymousClass0J2;
import X.AnonymousClass0pW;
import X.AnonymousClass1fC;
import X.AnonymousClass1hU;
import X.AnonymousClass1iH;
import X.AnonymousClass1n9;
import X.AnonymousClass1tm;
import X.AnonymousClass1uD;
import X.C10541tl;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.inject.ApplicationScoped;
import com.facebook.quicklog.QuickPerformanceLogger;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.perflogs.OculusPerformanceLogger;
import com.oculus.perflogs.impl.fbquicklog.PerfLogsHelper;
import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_perflogs_impl_fbquicklog_PerfLogsHelper_ULSEP_BINDING_ID"})
@ThreadSafe
@ApplicationScoped
public final class FbQuicklogPerformanceLogger implements OculusPerformanceLogger {
    public static volatile FbQuicklogPerformanceLogger _UL__ULSEP_com_oculus_perflogs_impl_fbquicklog_FbQuicklogPerformanceLogger_ULSEP_INSTANCE;
    public final QuickPerformanceLogger logger;

    @Override // com.oculus.perflogs.OculusPerformanceLogger
    public final void markerEnd(int i, short s) {
        this.logger.markerEnd(i, s);
    }

    @Override // com.oculus.perflogs.OculusPerformanceLogger
    public final void markerStart(int i) {
        this.logger.markerStart(i);
    }

    @Inject
    public FbQuicklogPerformanceLogger(PerfLogsHelper perfLogsHelper) {
        PerfLogsHelper.AnonymousClass1 r1 = new Provider<AnonymousClass1n9>() {
            /* class com.oculus.perflogs.impl.fbquicklog.PerfLogsHelper.AnonymousClass1 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // javax.inject.Provider
            public final AnonymousClass1n9 get() {
                return AnonymousClass0J2.A03(0, 85, PerfLogsHelper.this._UL_mInjectionContext);
            }
        };
        AnonymousClass1uD r2 = new AnonymousClass1uD();
        AwakeTimeSinceBootClock awakeTimeSinceBootClock = AwakeTimeSinceBootClock.INSTANCE;
        AnonymousClass0pW r4 = AnonymousClass0pW.A00;
        PerfLogsHelper.AnonymousClass2 r5 = new Object() {
            /* class com.oculus.perflogs.impl.fbquicklog.PerfLogsHelper.AnonymousClass2 */
        };
        AnonymousClass1hU r6 = new AnonymousClass1hU();
        AnonymousClass1iH r7 = AnonymousClass1iH.A00;
        if (r7 == null) {
            r7 = new AnonymousClass1iH();
            AnonymousClass1iH.A00 = r7;
        }
        AnonymousClass1fC r0 = new AnonymousClass1fC(r1, r2, awakeTimeSinceBootClock, r4, r5, r6, r7, new C10541tl());
        this.logger = new AnonymousClass1tm(r0.A08, r0.A03, r0.A02, r0.A01, r0.A07, r0.A05, r0.A04, r0.A00, r0.A06);
    }
}
