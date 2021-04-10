package com.oculus.unifiedtelemetry.unifiedlogging;

import X.AbstractC0247Xu;
import X.QC;
import X.eJ;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.logging.analytics2.LoggingUser;

@Dependencies({"_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_MobileConfigClient_ULSEP_BINDING_ID"})
public class SystemLoggingUser extends LoggingUser {
    public QC _UL_mInjectionContext;
    @Inject
    public final eJ<Credentials> mCredentialsProvider;

    @Inject
    public SystemLoggingUser(AbstractC0247Xu xu) {
        super(true);
        this._UL_mInjectionContext = new QC(1, xu);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(xu);
    }
}
