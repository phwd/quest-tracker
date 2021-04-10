package com.oculus.common.init.impl;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsInitInEnterpriseMode_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID"})
public class AppInitializer {
    public static final String TAG = "AppInitializer";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public AppInitializer(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(4, r3);
    }
}
