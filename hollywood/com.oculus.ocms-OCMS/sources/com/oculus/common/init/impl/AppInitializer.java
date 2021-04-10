package com.oculus.common.init.impl;

import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.common.init.AppInitModule;
import com.oculus.common.init.INeedInit;
import com.oculus.common.init.impl.ImplModule;
import com.oculus.managed.ManagedMode;
import com.oculus.managed.ManagedModule;
import java.util.Set;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsInitInEnterpriseMode_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID"})
public class AppInitializer {
    private static final String TAG = "AppInitializer";
    private InjectionContext _UL_mInjectionContext;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_common_init_impl_AppInitializer_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(ImplModule.UL_id._UL__ULSEP_com_oculus_common_init_impl_AppInitializer_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final AppInitializer _UL__ULSEP_com_oculus_common_init_impl_AppInitializer_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (AppInitializer) UL.factorymap.get(ImplModule.UL_id._UL__ULSEP_com_oculus_common_init_impl_AppInitializer_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final AppInitializer _UL__ULSEP_com_oculus_common_init_impl_AppInitializer_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new AppInitializer(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_common_init_impl_AppInitializer_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(ImplModule.UL_id._UL__ULSEP_com_oculus_common_init_impl_AppInitializer_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public AppInitializer(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(4, injectorLike);
    }

    public void run() {
        BLog.d(TAG, "Initializing %d NeedsHighPriorityInit", Integer.valueOf(((Set) FbInjector.lazyInstance(0, AppInitModule.UL_id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_BINDING_ID, this._UL_mInjectionContext)).size()));
        for (INeedInit iNeedInit : (Set) FbInjector.lazyInstance(0, AppInitModule.UL_id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_BINDING_ID, this._UL_mInjectionContext)) {
            BLog.d(TAG, "Initializing module: %s", iNeedInit.getClass().getSimpleName());
            iNeedInit.init();
        }
        if (((ManagedMode) FbInjector.lazyInstance(3, ManagedModule.UL_id._UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isEnterpriseModeEnabled()) {
            BLog.d(TAG, "Initializing %d NeedsInitInEnterpriseMode", Integer.valueOf(((Set) FbInjector.lazyInstance(1, AppInitModule.UL_id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsInitInEnterpriseMode_ULSEP_BINDING_ID, this._UL_mInjectionContext)).size()));
            for (INeedInit iNeedInit2 : (Set) FbInjector.lazyInstance(1, AppInitModule.UL_id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsInitInEnterpriseMode_ULSEP_BINDING_ID, this._UL_mInjectionContext)) {
                BLog.d(TAG, "Initializing module: %s", iNeedInit2.getClass().getSimpleName());
                iNeedInit2.init();
            }
        }
        BLog.d(TAG, "Initializing %d INeedInit modules", Integer.valueOf(((Set) FbInjector.lazyInstance(2, AppInitModule.UL_id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID, this._UL_mInjectionContext)).size()));
        for (INeedInit iNeedInit3 : (Set) FbInjector.lazyInstance(2, AppInitModule.UL_id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID, this._UL_mInjectionContext)) {
            BLog.d(TAG, "Initializing module: %s", iNeedInit3.getClass().getSimpleName());
            iNeedInit3.init();
        }
    }
}