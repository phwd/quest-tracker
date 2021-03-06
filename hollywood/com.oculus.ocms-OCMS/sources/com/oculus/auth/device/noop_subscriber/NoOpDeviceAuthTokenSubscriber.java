package com.oculus.auth.device.noop_subscriber;

import android.content.Context;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.auth.device.DeviceAuthTokenSubscriber;
import com.oculus.auth.device.noop_subscriber.NoOpDeviceAuthTokenSubscriberModule;
import javax.inject.Provider;

@Dependencies({})
@ApplicationScoped
public class NoOpDeviceAuthTokenSubscriber implements DeviceAuthTokenSubscriber {
    private static volatile NoOpDeviceAuthTokenSubscriber _UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_INSTANCE;

    @Override // com.oculus.auth.device.DeviceAuthTokenSubscriber
    public void onTokenRefresh(Context context, String str) {
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(NoOpDeviceAuthTokenSubscriberModule.UL_id._UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final NoOpDeviceAuthTokenSubscriber _UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (NoOpDeviceAuthTokenSubscriber) UL.factorymap.get(NoOpDeviceAuthTokenSubscriberModule.UL_id._UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_BINDING_ID, injectorLike);
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

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(NoOpDeviceAuthTokenSubscriberModule.UL_id._UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_BINDING_ID, injectorLike);
    }
}
