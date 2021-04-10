package com.oculus.horizon.vr_lifecycle;

import X.AnonymousClass0MD;
import X.AnonymousClass0VF;
import X.AnonymousClass0jg;
import X.AnonymousClass0lg;
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

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    public static final void _UL_injectMe(Context context, VRLifecycleStartUpBootReceiver vRLifecycleStartUpBootReceiver) {
        _UL_staticInjectMe((AnonymousClass0lg) AnonymousClass0VF.get(context), vRLifecycleStartUpBootReceiver);
    }

    public static final void _UL_staticInjectMe(AnonymousClass0lg r1, VRLifecycleStartUpBootReceiver vRLifecycleStartUpBootReceiver) {
        vRLifecycleStartUpBootReceiver.mJobSchedulerLazy = VRLifecycleJobScheduler._UL__ULSEP_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleJobScheduler_ULSEP_ACCESS_METHOD(r1);
        vRLifecycleStartUpBootReceiver.mVRLifecycleSessionManager = VRLifecycleSessionManager._UL__ULSEP_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleSessionManager_ULSEP_ACCESS_METHOD(r1);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public void onReceive(Context context, Intent intent, AnonymousClass0jg r5) {
        _UL_injectMe(context, this);
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            this.mVRLifecycleSessionManager.markSessionEndIfNotRecentAndInfoExists(new VRLifecycleSessionManager.MarkSessionEndCallback() {
                /* class com.oculus.horizon.vr_lifecycle.VRLifecycleStartUpBootReceiver.AnonymousClass1 */

                @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
                public void onError(ApiError apiError) {
                    AnonymousClass0MD.A0C(VRLifecycleStartUpBootReceiver.TAG, apiError, "Failed to mark session end.");
                    VRLifecycleStartUpBootReceiver.this.mJobSchedulerLazy.schedule();
                }

                @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
                public void onNoSessionInfo() {
                }

                @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
                public void onTooRecent() {
                    VRLifecycleStartUpBootReceiver.this.mJobSchedulerLazy.schedule();
                }

                @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
                public void onSuccess(GraphQLVRLifecycleResponse graphQLVRLifecycleResponse) {
                }
            });
        }
    }
}
