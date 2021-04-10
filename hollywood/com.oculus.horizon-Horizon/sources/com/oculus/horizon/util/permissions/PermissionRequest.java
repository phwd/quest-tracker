package com.oculus.horizon.util.permissions;

import android.annotation.SuppressLint;
import android.content.Context;

@SuppressLint({"BadSuperClassBroadcastReceiver.SecureBroadcastReceiver"})
public class PermissionRequest {
    public static final String ACTION_NAME_INTENT_KEY = "platform_action_name";
    public static final String HOME_PACKAGE_NAME = "com.oculus.home";
    public static final double MIN_HOME_VERSION_REQUIRED = 3.46d;
    public static final String PERMISSION_CALLBACK_INTENT_ACTION_NAME = "com.oculus.horizon.permission_callback";
    public static final String PERMISSION_INTENT_ACTION_NAME = "com.oculus.horizon.vr_permission.GET";
    public static final String PERMISSION_INTENT_KEY = "requested_permission";
    public static final String PERMISSION_STATUS_GRANTED = "granted";
    public static final String PERMISSION_STATUS_INTENT_KEY = "permission_status";
    public static final String REQUEST_ID_INTENT_KEY = "platform_request_id";
    public static final String TAG = "PermissionRequest";
    public final Callback mCallback;
    public final Context mContext;

    public interface Callback {
        void A6R(boolean z, long j);
    }

    public PermissionRequest(Context context, Callback callback) {
        this.mCallback = callback;
        this.mContext = context;
    }
}
