package com.oculus.errorreporting.init;

import X.AbstractC06640p5;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID"})
public class ErrorReporterLoginHandler implements LoginHandler {
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    public final Provider<Credentials> mCredentialsProvider;

    @Override // com.oculus.auth.handler.LoginHandler
    public final AnonymousClass0DC<Void> afterLoginAsync() {
        String str;
        Credentials credentials = this.mCredentialsProvider.get();
        IErrorReporter iErrorReporter = (IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext);
        if (credentials == null) {
            str = null;
        } else {
            str = credentials.mUserId;
        }
        iErrorReporter.A7i(str);
        return AnonymousClass0DC.A04(null);
    }

    @Inject
    public ErrorReporterLoginHandler(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r3);
    }
}
