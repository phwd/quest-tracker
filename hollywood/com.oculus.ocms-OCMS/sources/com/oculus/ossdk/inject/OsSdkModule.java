package com.oculus.ossdk.inject;

import android.content.Context;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.ForAppContext;
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
import com.oculus.os.Controllers;
import com.oculus.os.DeviceAuth;
import com.oculus.os.SettingsManager;
import javax.inject.Provider;

@InjectorModule
public class OsSdkModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_os_Controllers_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_os_Controllers_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(Controllers.class)));
        public static final int _UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(DeviceAuth.class)));
        public static final int _UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(SettingsManager.class)));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_os_DeviceAuth_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_os_SettingsManager_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForOsSdkModule {
        AutoGeneratedBindingsForOsSdkModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(BundledAndroidModule.class);
                binder.bind(Controllers.class).toProvider(new ControllersMethodAutoProvider());
                binder.bind(DeviceAuth.class).toProvider(new DeviceAuthMethodAutoProvider());
                binder.bind(SettingsManager.class).toProvider(new SettingsManagerMethodAutoProvider());
            }
        }
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_os_Controllers_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_oculus_os_Controllers_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_os_DeviceAuth_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_os_SettingsManager_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Controllers _UL__ULSEP_com_oculus_os_Controllers_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (Controllers) UL.factorymap.get(UL_id._UL__ULSEP_com_oculus_os_Controllers_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final Controllers _UL__ULSEP_com_oculus_os_Controllers_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideControllers();
    }

    @AutoGeneratedAccessMethod
    public static final DeviceAuth _UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (DeviceAuth) UL.factorymap.get(UL_id._UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final DeviceAuth _UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideDeviceAuth(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final SettingsManager _UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (SettingsManager) UL.factorymap.get(UL_id._UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final SettingsManager _UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideSettingsManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_os_Controllers_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_oculus_os_Controllers_ULSEP_BINDING_ID, injectorLike);
    }

    @ProviderMethod
    static SettingsManager provideSettingsManager(@ForAppContext Context context) {
        return new SettingsManager(context);
    }

    @ProviderMethod
    static Controllers provideControllers() {
        return new Controllers((Controllers.ControllerStatusObserver) null);
    }

    @ProviderMethod
    static DeviceAuth provideDeviceAuth(@ForAppContext Context context) {
        return new DeviceAuth(context);
    }
}
