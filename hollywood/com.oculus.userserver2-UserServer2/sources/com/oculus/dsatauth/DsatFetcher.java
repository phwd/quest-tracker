package com.oculus.dsatauth;

import X.AbstractC0192Xx;
import X.Om;
import X.SZ;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;

@Dependencies({"_UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID"})
@ApplicationScoped
public class DsatFetcher {
    public static volatile DsatFetcher _UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_INSTANCE;
    public Om _UL_mInjectionContext;
    @Inject
    public final AbstractC0192Xx<Credentials> mCredentialsProvider;
    public volatile boolean mIsDeviceMissingIdentity;

    @Inject
    public DsatFetcher(SZ sz) {
        this._UL_mInjectionContext = new Om(1, sz);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(sz);
    }
}
