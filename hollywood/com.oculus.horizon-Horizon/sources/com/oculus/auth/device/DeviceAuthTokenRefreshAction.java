package com.oculus.auth.device;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QB;
import X.AnonymousClass0b8;
import X.AnonymousClass0b9;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

public class DeviceAuthTokenRefreshAction implements AnonymousClass0b8, AnonymousClass0QB {
    public static final String ACTION_NEW_TOKEN_AVAILABLE = "com.oculus.deviceauthservice.NEW_TOKEN_AVAILABLE";
    public static final String TAG = "DeviceAuthTokenRefreshAction";
    @Inject
    @Eager
    public DeviceAuthTokenStore mDeviceAuthTokenStore;

    public static final void _UL_injectMe(Context context, DeviceAuthTokenRefreshAction deviceAuthTokenRefreshAction) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), deviceAuthTokenRefreshAction);
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r0, DeviceAuthTokenRefreshAction deviceAuthTokenRefreshAction) {
        deviceAuthTokenRefreshAction.mDeviceAuthTokenStore = DeviceAuthTokenStore._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenStore_ULSEP_ACCESS_METHOD(r0);
    }

    @Override // X.AnonymousClass0b8
    public void onReceive(Context context, Intent intent, AnonymousClass0b9 r4) {
        _UL_injectMe(context, this);
        this.mDeviceAuthTokenStore.fetchToken();
    }
}
