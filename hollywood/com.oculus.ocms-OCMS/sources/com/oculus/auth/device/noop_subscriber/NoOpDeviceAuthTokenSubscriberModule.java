package com.oculus.auth.device.noop_subscriber;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.Lazy;
import com.facebook.inject.ProviderMethod;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.auth.device.DeviceAuthTokenRefreshAction;
import com.oculus.auth.device.DeviceAuthTokenRefreshActionAutoProvider;
import com.oculus.auth.device.DeviceAuthTokenStore;
import com.oculus.auth.device.DeviceAuthTokenStoreAutoProvider;
import com.oculus.auth.device.DeviceAuthTokenSubscriber;
import javax.inject.Provider;

@InjectorModule
public class NoOpDeviceAuthTokenSubscriberModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenStore_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenStore_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(DeviceAuthTokenStore.class)));
        public static final int _UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(DeviceAuthTokenSubscriber.class)));
        public static final int _UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(NoOpDeviceAuthTokenSubscriber.class)));
    }

    @ProviderMethod(asDefault = true)
    static DeviceAuthTokenSubscriber providesNoOpDeviceAuthTokenSubscriber(NoOpDeviceAuthTokenSubscriber noOpDeviceAuthTokenSubscriber) {
        return noOpDeviceAuthTokenSubscriber;
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForNoOpDeviceAuthTokenSubscriberModule {
        AutoGeneratedBindingsForNoOpDeviceAuthTokenSubscriberModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(BundledAndroidModule.class);
                binder.bind(DeviceAuthTokenStore.class).toProvider(new DeviceAuthTokenStoreAutoProvider()).in(ApplicationScoped.class);
                binder.bindDefault(DeviceAuthTokenSubscriber.class).toProvider(new DeviceAuthTokenSubscriberMethodAutoProvider());
                binder.bind(NoOpDeviceAuthTokenSubscriber.class).toProvider(new NoOpDeviceAuthTokenSubscriberAutoProvider()).in(ApplicationScoped.class);
                binder.bindComponent(DeviceAuthTokenRefreshAction.class).toProvider(new DeviceAuthTokenRefreshActionAutoProvider());
            }
        }
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final DeviceAuthTokenSubscriber _UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (DeviceAuthTokenSubscriber) UL.factorymap.get(UL_id._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final DeviceAuthTokenSubscriber _UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return providesNoOpDeviceAuthTokenSubscriber(NoOpDeviceAuthTokenSubscriber._UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULSEP_BINDING_ID, injectorLike);
    }
}
