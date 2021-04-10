package com.oculus.horizon.vr_lifecycle;

import android.content.Context;
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
}
