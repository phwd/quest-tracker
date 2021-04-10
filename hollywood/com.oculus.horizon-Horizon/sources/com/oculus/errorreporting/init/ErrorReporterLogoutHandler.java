package com.oculus.errorreporting.init;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.handler.LogoutHandler;
import com.oculus.errorreporting.interfaces.IErrorReporter;

@Dependencies({"_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID"})
public class ErrorReporterLogoutHandler implements LogoutHandler {
    public AnonymousClass0QC _UL_mInjectionContext;

    @Override // com.oculus.auth.handler.LogoutHandler
    public final void beforeLogout() {
        ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A7i(null);
    }

    @Inject
    public ErrorReporterLogoutHandler(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
