package com.oculus.auth.service;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

public class DeviceTokenRefreshService extends IntentService {
    public static final String ACTION_DEVICE_SCOPED_TOKEN_REFRESH = "com.oculus.auth.ACTION_DEVICE_SCOPED_TOKEN_REFRESH";
    public static final String EXTRA_DEVICE_TOKEN = "com.oculus.auth.DEVICE_TOKEN";
    public static final String TAG = "DeviceTokenRefreshService";
    public AnonymousClass0QC _UL_mInjectionContext;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, DeviceTokenRefreshService deviceTokenRefreshService) {
        deviceTokenRefreshService._UL_mInjectionContext = new AnonymousClass0QC(1, r2);
    }

    public DeviceTokenRefreshService() {
        super(TAG);
    }

    public void onHandleIntent(Intent intent) {
        String str;
        String str2;
        if (intent == null) {
            str = TAG;
            str2 = "Intent is null";
        } else {
            String action = intent.getAction();
            if (action == null) {
                str = TAG;
                str2 = "Intent action is null";
            } else if (action.hashCode() == -1818314476 && action.equals(ACTION_DEVICE_SCOPED_TOKEN_REFRESH)) {
                ((AuthManager) AnonymousClass0J2.A03(0, 349, this._UL_mInjectionContext)).performDeviceScopedTokenRefresh(intent);
                return;
            } else {
                return;
            }
        }
        AnonymousClass0NO.A08(str, str2);
    }

    public static final void _UL_injectMe(Context context, DeviceTokenRefreshService deviceTokenRefreshService) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), deviceTokenRefreshService);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }
}
