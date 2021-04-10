package com.oculus.auth.device;

import com.facebook.inject.AbstractComponentProvider;

public class DeviceAuthTokenRefreshActionAutoProvider extends AbstractComponentProvider<DeviceAuthTokenRefreshAction> {
    public void inject(DeviceAuthTokenRefreshAction deviceAuthTokenRefreshAction) {
        DeviceAuthTokenRefreshAction._UL_staticInjectMe(this, deviceAuthTokenRefreshAction);
    }

    public boolean equals(Object obj) {
        return obj instanceof DeviceAuthTokenRefreshActionAutoProvider;
    }
}
