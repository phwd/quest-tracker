package com.oculus.util.device;

import com.facebook.inject.AbstractProvider;

public class DeviceUtilsAutoProvider extends AbstractProvider<DeviceUtils> {
    @Override // javax.inject.Provider
    public DeviceUtils get() {
        return new DeviceUtils(this);
    }
}
