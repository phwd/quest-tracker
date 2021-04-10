package com.oculus.auth.device.noop_subscriber;

import android.content.Context;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.device.DeviceAuthTokenSubscriber;

@Dependencies
@ApplicationScoped
public class NoOpDeviceAuthTokenSubscriber implements DeviceAuthTokenSubscriber {
    private static volatile NoOpDeviceAuthTokenSubscriber _UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_INSTANCE;

    @Override // com.oculus.auth.device.DeviceAuthTokenSubscriber
    public void onTokenRefresh(Context context, String str) {
    }

    @AutoGeneratedFactoryMethod
    public static final NoOpDeviceAuthTokenSubscriber _UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_INSTANCE == null) {
            synchronized (NoOpDeviceAuthTokenSubscriber.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        injectorLike.getApplicationInjector();
                        _UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_INSTANCE = new NoOpDeviceAuthTokenSubscriber();
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_INSTANCE;
    }

    @Inject
    NoOpDeviceAuthTokenSubscriber() {
    }
}
