package com.oculus.appmanager.installer.common;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.errorreporting.interfaces.InterfacesModule;

@Generated({"By: InjectorProcessor"})
public class InstallerDownloadStatusCheckerAutoProvider extends AbstractProvider<InstallerDownloadStatusChecker> {
    @Override // javax.inject.Provider
    public InstallerDownloadStatusChecker get() {
        return new InstallerDownloadStatusChecker(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this), InterfacesModule._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_ACCESS_METHOD(this));
    }
}
