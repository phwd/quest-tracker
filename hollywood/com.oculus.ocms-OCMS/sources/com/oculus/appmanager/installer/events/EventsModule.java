package com.oculus.appmanager.installer.events;

import com.facebook.inject.AbstractLibraryModule;
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
import javax.inject.Provider;

@InjectorModule
public class EventsModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_events_InstallerEventBus_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_appmanager_installer_events_InstallerEventBus_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(InstallerEventBus.class)));
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_installer_events_InstallerEventBus_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_oculus_appmanager_installer_events_InstallerEventBus_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_installer_events_InstallerEventBus_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_oculus_appmanager_installer_events_InstallerEventBus_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForEventsModule {
        AutoGeneratedBindingsForEventsModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(BundledAndroidModule.class);
                binder.bind(InstallerEventBus.class).toProvider(new InstallerEventBusMethodAutoProvider());
            }
        }
    }

    @AutoGeneratedAccessMethod
    public static final InstallerEventBus _UL__ULSEP_com_oculus_appmanager_installer_events_InstallerEventBus_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (InstallerEventBus) UL.factorymap.get(UL_id._UL__ULSEP_com_oculus_appmanager_installer_events_InstallerEventBus_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final InstallerEventBus _UL__ULSEP_com_oculus_appmanager_installer_events_InstallerEventBus_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return providerInstallerEventBus();
    }

    @ProviderMethod
    static InstallerEventBus providerInstallerEventBus() {
        return InstallerEventBus.getInstance();
    }
}
