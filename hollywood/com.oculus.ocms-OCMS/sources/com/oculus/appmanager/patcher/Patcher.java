package com.oculus.appmanager.patcher;

import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.UL;
import com.oculus.appmanager.patcher.PatcherModule;
import javax.inject.Inject;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_appmanager_patcher_RsyncNativeLibrary_ULSEP_BINDING_ID"})
@ApplicationScoped
public class Patcher {
    private static volatile Patcher _UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_INSTANCE;
    private RsyncNativeLibrary mRsyncNativeLibrary;

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_patcher_Patcher_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(PatcherModule.UL_id._UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_patcher_Patcher_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(PatcherModule.UL_id._UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Patcher _UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (Patcher) UL.factorymap.get(PatcherModule.UL_id._UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final Patcher _UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_INSTANCE == null) {
            synchronized (Patcher.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_INSTANCE = new Patcher(RsyncNativeLibrary._UL__ULSEP_com_oculus_appmanager_patcher_RsyncNativeLibrary_ULSEP_ACCESS_METHOD(injectorLike.getApplicationInjector()));
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_INSTANCE;
    }

    @Inject
    public Patcher(RsyncNativeLibrary rsyncNativeLibrary) {
        this.mRsyncNativeLibrary = rsyncNativeLibrary;
    }

    public void patchFile(String str, String str2, String str3) {
        this.mRsyncNativeLibrary.ensureLoaded();
        RsyncNativeLibrary rsyncNativeLibrary = this.mRsyncNativeLibrary;
        RsyncNativeLibrary.patch(str, str2, str3);
    }
}
