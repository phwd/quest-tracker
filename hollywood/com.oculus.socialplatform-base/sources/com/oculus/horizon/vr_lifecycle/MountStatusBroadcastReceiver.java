package com.oculus.horizon.vr_lifecycle;

import X.AnonymousClass0MD;
import X.AnonymousClass0jg;
import android.content.Context;
import android.content.Intent;
import com.oculus.horizon.vr_lifecycle.MountStatusBase;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;
import javax.annotation.Nullable;

public class MountStatusBroadcastReceiver extends MountStatusBase {
    public static final String ACTION_MOUNT_STATE_CHANGED = "com.oculus.intent.action.MOUNT_STATE_CHANGED";
    public static final String STATE_BUNDLE_KEY = "state";
    public static final String TAG = "MountStatusBroadcastReceiver";
    @Nullable
    public OculusPublicBroadcastReceiver mBroadcastReceiver = null;
    @Nullable
    public Context mContext = null;

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    @Override // com.oculus.horizon.vr_lifecycle.MountStatusBase
    public boolean isRunning() {
        if (this.mBroadcastReceiver != null) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.horizon.vr_lifecycle.MountStatusBase
    public void start(Context context, final MountStatusBase.Callback callback) {
        String str;
        String str2;
        if (this.mBroadcastReceiver != null) {
            str = TAG;
            str2 = "Attempting to call start when broadcast receiver is already registered.";
        } else if (context == null) {
            str = TAG;
            str2 = "Null context, bailing";
        } else if (callback == null) {
            str = TAG;
            str2 = "Null callback.  Aborting start, this would have no effect";
        } else {
            this.mContext = context;
            AnonymousClass1 r1 = new OculusPublicBroadcastReceiver(new String[]{"com.oculus.intent.action.MOUNT_STATE_CHANGED"}) {
                /* class com.oculus.horizon.vr_lifecycle.MountStatusBroadcastReceiver.AnonymousClass1 */

                @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
                public void onReceive(Context context, Intent intent, AnonymousClass0jg r6) {
                    if (intent.getAction().equals("com.oculus.intent.action.MOUNT_STATE_CHANGED")) {
                        callback.onMountStatusChanged(intent.getBooleanExtra("state", true));
                        return;
                    }
                    AnonymousClass0MD.A09(MountStatusBroadcastReceiver.TAG, "Unknown action: %s", intent.getAction());
                }
            };
            this.mBroadcastReceiver = r1;
            r1.registerReceiver(this.mContext);
            return;
        }
        AnonymousClass0MD.A04(str, str2);
    }

    @Override // com.oculus.horizon.vr_lifecycle.MountStatusBase
    public void teardown() {
        OculusPublicBroadcastReceiver oculusPublicBroadcastReceiver;
        Context context = this.mContext;
        if (!(context == null || (oculusPublicBroadcastReceiver = this.mBroadcastReceiver) == null)) {
            oculusPublicBroadcastReceiver.unregisterReceiver(context);
        }
        this.mBroadcastReceiver = null;
        this.mContext = null;
    }
}
