package com.oculus.common.packagescache;

import X.Om;
import X.SZ;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID"})
@ApplicationScoped
public class PackageManagerUtils {
    public static volatile PackageManagerUtils _UL__ULSEP_com_oculus_common_packagescache_PackageManagerUtils_ULSEP_INSTANCE;
    public Om _UL_mInjectionContext;

    @Inject
    public PackageManagerUtils(SZ sz) {
        this._UL_mInjectionContext = new Om(1, sz);
    }
}
