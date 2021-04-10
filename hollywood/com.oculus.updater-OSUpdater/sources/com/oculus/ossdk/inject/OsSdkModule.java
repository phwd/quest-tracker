package com.oculus.ossdk.inject;

import android.content.Context;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.os.Controllers;
import com.oculus.os.DeviceAuth;
import com.oculus.os.SettingsManager;

@InjectorModule
public class OsSdkModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_os_Controllers_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_os_Controllers_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(Controllers.class)));
        public static final int _UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(DeviceAuth.class)));
        public static final int _UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(SettingsManager.class)));
    }

    @AutoGeneratedFactoryMethod
    public static final DeviceAuth _UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideDeviceAuth(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedFactoryMethod
    public static final SettingsManager _UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideSettingsManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @ProviderMethod
    static SettingsManager provideSettingsManager(@ForAppContext Context context) {
        return new SettingsManager(context);
    }

    @ProviderMethod
    static DeviceAuth provideDeviceAuth(@ForAppContext Context context) {
        return new DeviceAuth(context);
    }
}