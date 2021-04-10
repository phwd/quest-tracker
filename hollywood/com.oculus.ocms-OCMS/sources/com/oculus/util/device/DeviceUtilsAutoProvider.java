package com.oculus.util.device;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class DeviceUtilsAutoProvider extends AbstractProvider<DeviceUtils> {
    @Override // javax.inject.Provider
    public DeviceUtils get() {
        return new DeviceUtils(this);
    }
}
