package com.oculus.auth.device;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class DeviceAuthTokenStoreAutoProvider extends AbstractProvider<DeviceAuthTokenStore> {
    @Override // javax.inject.Provider
    public DeviceAuthTokenStore get() {
        return new DeviceAuthTokenStore(this);
    }
}
