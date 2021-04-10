package com.oculus.horizon.vr_lifecycle;

import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0b9;
import X.AnonymousClass117;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.Eager;
import com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager;
import com.oculus.horizon.vr_lifecycle.query.GraphQLVRLifecycleResponse;
import com.oculus.http.core.base.ApiError;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;
import javax.inject.Inject;

public class VRLifecycleStartUpBootReceiver extends OculusPublicBroadcastReceiver {
    public static final String TAG = "VRLifecycleStartUpBootReceiver";
    @Inject
    @Eager
    public VRLifecycleJobScheduler mJobSchedulerLazy;
    @Inject
    @Eager
    public VRLifecycleSessionManager mVRLifecycleSessionManager;

    public VRLifecycleStartUpBootReceiver() {
        super("android.intent.action.BOOT_COMPLETED");
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r5) {
        AnonymousClass0J2 r1 = AnonymousClass0J2.get(context);
        this.mJobSchedulerLazy = (VRLifecycleJobScheduler) AnonymousClass117.A00(404, r1);
        this.mVRLifecycleSessionManager = (VRLifecycleSessionManager) AnonymousClass117.A00(8, r1);
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            this.mVRLifecycleSessionManager.A02(new VRLifecycleSessionManager.MarkSessionEndCallback() {
                /* class com.oculus.horizon.vr_lifecycle.VRLifecycleStartUpBootReceiver.AnonymousClass1 */

                @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
                public final void A5z(ApiError apiError) {
                    AnonymousClass0NO.A0H(VRLifecycleStartUpBootReceiver.TAG, apiError, "Failed to mark session end.");
                    VRLifecycleStartUpBootReceiver.this.mJobSchedulerLazy.A00();
                }

                @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
                public final void A76() {
                    VRLifecycleStartUpBootReceiver.this.mJobSchedulerLazy.A00();
                }

                @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
                public final void A6L() {
                }

                @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
                public final void A75(GraphQLVRLifecycleResponse graphQLVRLifecycleResponse) {
                }
            });
        }
    }
}
