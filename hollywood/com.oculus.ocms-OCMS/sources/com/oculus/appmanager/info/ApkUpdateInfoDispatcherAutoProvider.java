package com.oculus.appmanager.info;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.errorreporting.interfaces.InterfacesModule;

@Generated({"By: InjectorProcessor"})
public class ApkUpdateInfoDispatcherAutoProvider extends AbstractProvider<ApkUpdateInfoDispatcher> {
    @Override // javax.inject.Provider
    public ApkUpdateInfoDispatcher get() {
        return new ApkUpdateInfoDispatcher(InterfacesModule._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_ACCESS_METHOD(this), InfoModule._UL__ULSEP_java_util_Set_ULLT_com_oculus_appmanager_info_ApkUpdateInfoListener_ULGT__ULSEP_ACCESS_METHOD(this));
    }
}
