package com.oculus.library.security;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class AccessControlAutoProvider extends AbstractProvider<AccessControl> {
    @Override // javax.inject.Provider
    public AccessControl get() {
        return new AccessControl(TrustedApplications._UL__ULSEP_com_oculus_library_security_TrustedApplications_ULSEP_ACCESS_METHOD(this));
    }
}
