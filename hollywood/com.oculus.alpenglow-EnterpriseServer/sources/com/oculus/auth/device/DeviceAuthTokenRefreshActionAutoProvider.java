package com.oculus.auth.device;

import X.AbstractC01770Lm;

public class DeviceAuthTokenRefreshActionAutoProvider extends AbstractC01770Lm<DeviceAuthTokenRefreshAction> {
    public boolean equals(Object obj) {
        return obj instanceof DeviceAuthTokenRefreshActionAutoProvider;
    }

    public void inject(DeviceAuthTokenRefreshAction deviceAuthTokenRefreshAction) {
        DeviceAuthTokenRefreshAction._UL_staticInjectMe(this, deviceAuthTokenRefreshAction);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        DeviceAuthTokenRefreshAction._UL_staticInjectMe(this, (DeviceAuthTokenRefreshAction) obj);
    }
}
