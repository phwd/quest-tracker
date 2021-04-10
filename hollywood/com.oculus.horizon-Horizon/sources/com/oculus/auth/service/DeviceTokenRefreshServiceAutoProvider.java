package com.oculus.auth.service;

import X.AnonymousClass0J9;

public class DeviceTokenRefreshServiceAutoProvider extends AnonymousClass0J9<DeviceTokenRefreshService> {
    public boolean equals(Object obj) {
        return obj instanceof DeviceTokenRefreshServiceAutoProvider;
    }

    public void inject(DeviceTokenRefreshService deviceTokenRefreshService) {
        DeviceTokenRefreshService._UL_staticInjectMe(this, deviceTokenRefreshService);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        DeviceTokenRefreshService._UL_staticInjectMe(this, (DeviceTokenRefreshService) obj);
    }
}
