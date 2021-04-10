package com.oculus.horizon.push;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import X.AnonymousClass0aL;
import android.content.Context;
import android.os.Looper;
import android.os.MessageQueue;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.base.app.AppInfo;
import com.oculus.common.init.INeedInit;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_utils_TelemetryStateMonitor_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_profile_UserProfileHelper_ULSEP_BINDING_ID"})
public class FbnsPushInit implements INeedInit {
    public static final String TAG = "FbnsPushInit";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public FbnsPushInit(AbstractC06640p5 r3, @ForAppContext Context context, AppInfo appInfo) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r3);
        AnonymousClass0aL.A00(context, appInfo.appId);
    }

    @Override // com.oculus.common.init.INeedInit
    public final void init() {
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            /* class com.oculus.horizon.push.FbnsPushInit.AnonymousClass1 */

            /* JADX WARNING: Code restructure failed: missing block: B:10:0x0055, code lost:
                if (r0.mTelemetryState != com.oculus.unifiedtelemetry.unifiedlogging.utils.SettingsManager.TelemetryState.OFF) goto L_0x0057;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final boolean queueIdle() {
                /*
                // Method dump skipped, instructions count: 292
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.push.FbnsPushInit.AnonymousClass1.queueIdle():boolean");
            }
        });
    }
}
