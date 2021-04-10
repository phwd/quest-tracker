package com.oculus.auth.credentials;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class CredentialsMethodAutoProvider extends AbstractProvider<Credentials> {
    @Override // javax.inject.Provider
    public Credentials get() {
        return CredentialsModule.provideCredentials(CredentialsModule._UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_ACCESS_METHOD(this));
    }
}
