package com.oculus.util.service;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.errorreporting.interfaces.InterfacesModule;

@Generated({"By: InjectorProcessor"})
public class ServiceFuturesAutoProvider extends AbstractProvider<ServiceFutures> {
    @Override // javax.inject.Provider
    public ServiceFutures get() {
        return new ServiceFutures(InterfacesModule._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_ACCESS_METHOD(this));
    }
}
