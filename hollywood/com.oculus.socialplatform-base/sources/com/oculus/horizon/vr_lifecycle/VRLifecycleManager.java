package com.oculus.horizon.vr_lifecycle;

import X.AbstractC03180ld;
import X.AnonymousClass0Hr;
import X.AnonymousClass0MD;
import X.AnonymousClass0VC;
import X.AnonymousClass0jg;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import X.C00610Hs;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.vr.VRStateManager;
import com.oculus.horizon.vr_lifecycle.MountStatusBase;
import com.oculus.horizon.weak_ref_counter.SelfReapingRefCounter;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;
import com.oculus.util.device.DeviceUtils;
import javax.inject.Provider;

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
    public final AbstractC03180ld<VRLifecycleSessionManager> mSessionManagerLazy;
    @Inject
    @Eager
    public final VRStateManager mVRStateManager;

    public interface VRLifecycle {
        void onHeadsetMounted();

        void onHeadsetUnmounted();

        void onVRSessionEnded();

        void onVRSessionStarted();
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleManager_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(37, r1);
    }

    @AutoGeneratedAccessMethod
    public static final VRLifecycleManager _UL__ULSEP_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleManager_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (VRLifecycleManager) AnonymousClass1TK.A00(37, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleManager_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(37, r1);
    }

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    public boolean isMountStatePollerRunning() {
        return this.mMountStatuser.isRunning();
    }

    public void notifyClientsHeadsetMountStateChanged(final boolean z) {
        this.mSessionManagerLazy.get().onHeadsetMountStateChanged(z);
        doSomethingQuicklyOnEveryClient(new SelfReapingRefCounter.DoSomething<VRLifecycle>() {
            /* class com.oculus.horizon.vr_lifecycle.VRLifecycleManager.AnonymousClass1 */

            public void doSomethingQuicklyUnderMutex(VRLifecycle vRLifecycle) {
                if (z) {
                    AnonymousClass0MD.A05(VRLifecycleManager.TAG, "onHeadsetMounted");
                    vRLifecycle.onHeadsetMounted();
                    return;
                }
                AnonymousClass0MD.A05(VRLifecycleManager.TAG, "onHeadsetUnmounted");
                vRLifecycle.onHeadsetUnmounted();
            }
        });
    }

    public void notifyClientsVRSessionEnded() {
        this.mVRStateManager.setIsDocked(false);
        doSomethingQuicklyOnEveryClient(new SelfReapingRefCounter.DoSomething<VRLifecycle>() {
            /* class com.oculus.horizon.vr_lifecycle.VRLifecycleManager.AnonymousClass2 */

            public void doSomethingQuicklyUnderMutex(VRLifecycle vRLifecycle) {
                AnonymousClass0MD.A05(VRLifecycleManager.TAG, "onVRSessionEnded");
                vRLifecycle.onVRSessionEnded();
            }
        });
    }

    public void notifyClientsVRSessionStarted() {
        this.mVRStateManager.setIsDocked(true);
        doSomethingQuicklyOnEveryClient(new SelfReapingRefCounter.DoSomething<VRLifecycle>() {
            /* class com.oculus.horizon.vr_lifecycle.VRLifecycleManager.AnonymousClass3 */

            public void doSomethingQuicklyUnderMutex(VRLifecycle vRLifecycle) {
                AnonymousClass0MD.A05(VRLifecycleManager.TAG, "onVRSessionStarted");
                vRLifecycle.onVRSessionStarted();
            }
        });
    }

    @Override // com.oculus.horizon.weak_ref_counter.SelfReapingRefCounter
    public void oneTimeSetup() {
        final String str;
        final String str2;
        this.mMountStatuser.start(this.mContext, new MountStatusBase.Callback() {
            /* class com.oculus.horizon.vr_lifecycle.VRLifecycleManager.AnonymousClass4 */

            @Override // com.oculus.horizon.vr_lifecycle.MountStatusBase.Callback
            public void onMountStatusChanged(boolean z) {
                VRLifecycleManager.this.notifyClientsHeadsetMountStateChanged(z);
            }
        });
        if (this.mDeviceUtils.mIsStandalone) {
            str = "android.intent.action.SCREEN_ON";
            str2 = "android.intent.action.SCREEN_OFF";
        } else {
            str = null;
            str2 = ACTION_HMT_DISCONNECT;
        }
        this.mSessionEndReceiver = new OculusPublicBroadcastReceiver(new String[0]) {
            /* class com.oculus.horizon.vr_lifecycle.VRLifecycleManager.AnonymousClass5 */

            @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
            public void onReceive(Context context, Intent intent, AnonymousClass0jg r5) {
                String action = intent.getAction();
                if (str2.equals(action)) {
                    VRLifecycleManager.this.notifyClientsVRSessionEnded();
                    return;
                }
                String str = str;
                if (str != null && str.equals(action)) {
                    VRLifecycleManager.this.notifyClientsVRSessionStarted();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(str2);
        if (str != null) {
            intentFilter.addAction(str);
        }
        OculusPublicBroadcastReceiver oculusPublicBroadcastReceiver = this.mSessionEndReceiver;
        oculusPublicBroadcastReceiver.mIntentFilter = intentFilter;
        oculusPublicBroadcastReceiver.registerReceiver(this.mContext);
    }

    @Override // com.oculus.horizon.weak_ref_counter.SelfReapingRefCounter
    public void oneTimeTeardown() {
        this.mMountStatuser.teardown();
        this.mSessionEndReceiver.unregisterReceiver(this.mContext);
    }

    @Inject
    public VRLifecycleManager(AnonymousClass0lg r2, @ForAppContext Context context, AbstractC03180ld<VRLifecycleSessionManager> r4) {
        this.mDeviceUtils = DeviceUtils._UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_ACCESS_METHOD(r2);
        this.mVRStateManager = VRStateManager._UL__ULSEP_com_oculus_horizon_vr_VRStateManager_ULSEP_ACCESS_METHOD(r2);
        this.mContext = context;
        this.mSessionManagerLazy = r4;
        if (this.mDeviceUtils.mIsStandalone) {
            this.mMountStatuser = new MountStatusBroadcastReceiver();
        } else {
            this.mMountStatuser = new MountStatusPoller();
        }
    }

    @AutoGeneratedFactoryMethod
    public static final VRLifecycleManager _UL__ULSEP_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleManager_ULSEP_FACTORY_METHOD(AnonymousClass0lg r3, Object obj) {
        return new VRLifecycleManager(r3, C00610Hs.A00(r3), VRLifecycleSessionManager._UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleSessionManager_ULGT__ULSEP_ACCESS_METHOD(r3));
    }
}
