package com.oculus.auth.credentials;

import com.facebook.inject.AbstractProvider;

public class CredentialsMethodAutoProvider extends AbstractProvider<Credentials> {
    @Override // javax.inject.Provider
    public Credentials get() {
        return CredentialsModule.provideCredentials(CredentialsModule.$ul_$xXXcom_oculus_auth_credentials_CredentialsManager$xXXACCESS_METHOD(this));
    }
}
