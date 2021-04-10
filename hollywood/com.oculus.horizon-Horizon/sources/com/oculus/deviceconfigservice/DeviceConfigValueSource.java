package com.oculus.deviceconfigservice;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum DeviceConfigValueSource {
    OVERRIDDEN_VALUE,
    DEVICE_WIDE_CACHE_VALUE,
    MOBILE_CONFIG_SERVICE_VALUE
}
