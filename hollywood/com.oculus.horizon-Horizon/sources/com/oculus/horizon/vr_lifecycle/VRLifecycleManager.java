package com.oculus.horizon.vr_lifecycle;

import X.AbstractC06640p5;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import android.content.Context;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.vr.VRStateManager;
import com.oculus.horizon.weak_ref_counter.SelfReapingRefCounter;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;
import com.oculus.util.device.DeviceUtils;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleSessionManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_vr_VRStateManager_ULSEP_BINDING_ID"})
public class VRLifecycleManager extends SelfReapingRefCounter<VRLifecycle> {
    public static final String ACTION_HMT_DISCONNECT = "com.samsung.intent.action.HMT_DISCONNECTED";
    public static final String TAG = "VRLifecycleManager";
    public final Context mContext;
    @Inject
    @Eager
    public final DeviceUtils mDeviceUtils;
    public final MountStatusBase mMountStatuser;
    public OculusPublicBroadcastReceiver mSessionEndReceiver;
    public final AnonymousClass0p1<VRLifecycleSessionManager> mSessionManagerLazy;
    @Inject
    @Eager
    public final VRStateManager mVRStateManager;

    public interface VRLifecycle {
        void A6C();

        void A6D();

        void A7A();

        void A7B();
    }

    @Inject
    public VRLifecycleManager(AbstractC06640p5 r2, @ForAppContext Context context, AnonymousClass0p1<VRLifecycleSessionManager> r4) {
        this.mDeviceUtils = DeviceUtils.A00(r2);
        this.mVRStateManager = (VRStateManager) AnonymousClass117.A00(325, r2);
        this.mContext = context;
        this.mSessionManagerLazy = r4;
        if (this.mDeviceUtils.A04()) {
            this.mMountStatuser = new MountStatusBroadcastReceiver();
        } else {
            this.mMountStatuser = new MountStatusPoller();
        }
    }
}
