package com.oculus.common.devicelifecycle;

public interface DeviceLifecycleObserver {
    void onDeviceMountedStateChange(boolean z);

    void onNetworkAvailabilityChange(boolean z);

    void onNetworkBlockedStateChange(boolean z);
}
