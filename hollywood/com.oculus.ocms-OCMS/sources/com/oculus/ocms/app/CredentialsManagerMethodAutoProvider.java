package com.oculus.ocms.app;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.auth.credentials.CredentialsManager;

@Generated({"By: InjectorProcessor"})
public class CredentialsManagerMethodAutoProvider extends AbstractProvider<CredentialsManager> {
    @Override // javax.inject.Provider
    public CredentialsManager get() {
        return OCMSAppModule.provideCredentialsManager(OCMSCredentialsManager._UL__ULSEP_com_oculus_ocms_app_OCMSCredentialsManager_ULSEP_ACCESS_METHOD(this));
    }
}
