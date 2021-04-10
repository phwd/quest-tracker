package com.oculus.auth.device.noop_subscriber;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.auth.device.DeviceAuthTokenSubscriber;

@Generated({"By: InjectorProcessor"})
public class DeviceAuthTokenSubscriberMethodAutoProvider extends AbstractProvider<DeviceAuthTokenSubscriber> {
    @Override // javax.inject.Provider
    public DeviceAuthTokenSubscriber get() {
        return NoOpDeviceAuthTokenSubscriberModule.providesNoOpDeviceAuthTokenSubscriber(NoOpDeviceAuthTokenSubscriber._UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_ACCESS_METHOD(this));
    }
}
