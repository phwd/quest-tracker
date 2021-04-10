package com.oculus.alpenglow.enterpriseserver;

import X.AnonymousClass0LT;
import com.oculus.auth.device.DeviceAuthTokenRefreshAction;

public class DeviceTokenBroadcastReceiver extends AnonymousClass0LT {
    public DeviceTokenBroadcastReceiver() {
        super(DeviceAuthTokenRefreshAction.ACTION_NEW_TOKEN_AVAILABLE, new DeviceAuthTokenRefreshAction());
    }
}
