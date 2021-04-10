package com.oculus.appmanager.installer.contract;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class InstallerServiceContractAutoProvider extends AbstractProvider<InstallerServiceContract> {
    @Override // javax.inject.Provider
    public InstallerServiceContract get() {
        return new InstallerServiceContract(this, BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
