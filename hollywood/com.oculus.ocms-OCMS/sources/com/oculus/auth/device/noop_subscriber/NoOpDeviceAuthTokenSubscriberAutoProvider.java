package com.oculus.auth.device.noop_subscriber;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class NoOpDeviceAuthTokenSubscriberAutoProvider extends AbstractProvider<NoOpDeviceAuthTokenSubscriber> {
    @Override // javax.inject.Provider
    public NoOpDeviceAuthTokenSubscriber get() {
        return new NoOpDeviceAuthTokenSubscriber();
    }
}
