package com.oculus.headlesshorizon.deviceauth;

import X.C00910Hi;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.device.DeviceAuthTokenRefreshAction;

@Dependencies({})
public class DeviceAuthTokenRefreshBroadcastReceiver extends C00910Hi {
    public static final String ACTION_NEW_TOKEN_AVAILABLE = "com.oculus.deviceauthservice.NEW_TOKEN_AVAILABLE";

    @Inject
    public DeviceAuthTokenRefreshBroadcastReceiver() {
        super("com.oculus.deviceauthservice.NEW_TOKEN_AVAILABLE", new DeviceAuthTokenRefreshAction());
    }
}
