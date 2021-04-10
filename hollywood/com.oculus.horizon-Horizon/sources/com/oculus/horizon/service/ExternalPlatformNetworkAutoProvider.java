package com.oculus.horizon.service;

import X.AnonymousClass0J3;
import X.AnonymousClass117;
import com.facebook.annotations.Generated;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.config.trusted_user.TrustedUserModule;
import com.oculus.http.core.ApiModule;
import com.oculus.unlockulus_helper.UnlockulusHelper;

@Generated({"By: InjectorProcessor"})
public class ExternalPlatformNetworkAutoProvider extends AnonymousClass0J3<ExternalPlatformNetwork> {
    public ExternalPlatformNetwork get() {
        return new ExternalPlatformNetwork(this, ApiModule.A0C(this), ExternalPlatformLocal._UL__ULSEP_com_oculus_horizon_service_ExternalPlatformLocal_ULSEP_ACCESS_METHOD(this), CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(this), (UnlockulusHelper) AnonymousClass117.A00(296, this), TrustedUserModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_is_ULUNDERSCORE_trusted_ULUNDERSCORE_user_ULSEP_ACCESS_METHOD(this));
    }
}
