package com.oculus.auth.device;

import android.content.Context;
import android.content.Intent;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectableComponentWithoutContext;
import com.facebook.inject.InjectorLike;
import com.facebook.secure.receiver.ActionReceiver;
import com.facebook.secure.receiver.BroadcastReceiverLike;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;

public class DeviceAuthTokenRefreshAction implements ActionReceiver, InjectableComponentWithoutContext {
    public static final String ACTION_NEW_TOKEN_AVAILABLE = "com.oculus.deviceauthservice.NEW_TOKEN_AVAILABLE";
    private static final String TAG = "DeviceAuthTokenRefreshAction";
    @Inject
    @Eager
    private DeviceAuthTokenStore mDeviceAuthTokenStore;

    private static final void _UL_injectMe(Context context, DeviceAuthTokenRefreshAction deviceAuthTokenRefreshAction) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), deviceAuthTokenRefreshAction);
        } else {
            FbInjector.injectMe(DeviceAuthTokenRefreshAction.class, (InjectableComponentWithoutContext) deviceAuthTokenRefreshAction, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, DeviceAuthTokenRefreshAction deviceAuthTokenRefreshAction) {
        deviceAuthTokenRefreshAction.mDeviceAuthTokenStore = DeviceAuthTokenStore._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenStore_ULSEP_ACCESS_METHOD(injectorLike);
    }

    @Override // com.facebook.secure.receiver.ActionReceiver
    public void onReceive(Context context, Intent intent, BroadcastReceiverLike broadcastReceiverLike) {
        _UL_injectMe(context, this);
        BLog.d(TAG, "Refresh auth token");
        this.mDeviceAuthTokenStore.fetchToken();
    }
}
