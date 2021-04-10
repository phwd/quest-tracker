package com.oculus.auth.device;

import X.AbstractC02990bJ;
import X.AbstractC04990iH;
import X.AbstractC05000iI;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R6;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

public class DeviceAuthTokenRefreshAction implements AbstractC04990iH, AnonymousClass0R6 {
    public static final String ACTION_NEW_TOKEN_AVAILABLE = "com.oculus.deviceauthservice.NEW_TOKEN_AVAILABLE";
    public static final String TAG = "DeviceAuthTokenRefreshAction";
    @Inject
    @Eager
    public DeviceAuthTokenStore mDeviceAuthTokenStore;

    public static final void _UL_injectMe(Context context, DeviceAuthTokenRefreshAction deviceAuthTokenRefreshAction) {
        _UL_staticInjectMe(AnonymousClass0Lh.get(context), deviceAuthTokenRefreshAction);
    }

    public static final void _UL_staticInjectMe(AbstractC02990bJ r0, DeviceAuthTokenRefreshAction deviceAuthTokenRefreshAction) {
        deviceAuthTokenRefreshAction.mDeviceAuthTokenStore = DeviceAuthTokenStore._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenStore_ULSEP_ACCESS_METHOD(r0);
    }

    @Override // X.AbstractC04990iH
    public void onReceive(Context context, Intent intent, AbstractC05000iI r4) {
        _UL_injectMe(context, this);
        this.mDeviceAuthTokenStore.fetchToken();
    }
}
