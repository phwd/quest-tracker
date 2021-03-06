package com.oculus.dsatauth;

import X.AbstractC0247Xu;
import X.Pj;
import X.QC;
import X.eJ;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;

@Dependencies({"_UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID"})
@ApplicationScoped
public class DsatFetcher {
    public static volatile DsatFetcher _UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    @Inject
    public final eJ<Credentials> mCredentialsProvider;
    public volatile boolean mIsDeviceMissingIdentity;

    @AutoGeneratedFactoryMethod
    public static final DsatFetcher A00(AbstractC0247Xu xu) {
        if (_UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_INSTANCE == null) {
            synchronized (DsatFetcher.class) {
                Pj A00 = Pj.A00(_UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_INSTANCE, xu);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_INSTANCE = new DsatFetcher(xu.getApplicationInjector());
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_INSTANCE;
    }

    @Inject
    public DsatFetcher(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(1, xu);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(xu);
    }
}
