package com.oculus.managed;

import android.os.Handler;
import android.os.Looper;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.managed.ManagedModule;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import com.oculus.ossdk.inject.OsSdkModule;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID"})
@ApplicationScoped
public class ManagedMode {
    private static volatile ManagedMode _UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_INSTANCE;
    private InjectionContext _UL_mInjectionContext;
    private boolean isEnterpriseModeEnabled;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_managed_ManagedMode_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(ManagedModule.UL_id._UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final ManagedMode _UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (ManagedMode) UL.factorymap.get(ManagedModule.UL_id._UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final ManagedMode _UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_INSTANCE == null) {
            synchronized (ManagedMode.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_INSTANCE = new ManagedMode(injectorLike.getApplicationInjector());
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_managed_ManagedMode_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(ManagedModule.UL_id._UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public ManagedMode(InjectorLike injectorLike) {
        boolean z = true;
        this._UL_mInjectionContext = new InjectionContext(1, injectorLike);
        this.isEnterpriseModeEnabled = ((SettingsManager) FbInjector.lazyInstance(0, OsSdkModule.UL_id._UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getInt("managed_device", 0) != 2 ? false : z;
        ((SettingsManager) FbInjector.lazyInstance(0, OsSdkModule.UL_id._UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).registerSettingsObserver("managed_device", new SettingsObserverCallback() {
            /* class com.oculus.managed.ManagedMode.AnonymousClass1 */

            public void onSettingChange(String str) {
                if ("managed_device".equals(str)) {
                    ManagedMode managedMode = ManagedMode.this;
                    boolean z = false;
                    if (((SettingsManager) FbInjector.lazyInstance(0, OsSdkModule.UL_id._UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID, ManagedMode.this._UL_mInjectionContext)).getInt("managed_device", 0) == 2) {
                        z = true;
                    }
                    managedMode.isEnterpriseModeEnabled = z;
                }
            }
        }, new Handler(Looper.getMainLooper()));
    }

    public boolean isEnterpriseModeEnabled() {
        return this.isEnterpriseModeEnabled;
    }
}
