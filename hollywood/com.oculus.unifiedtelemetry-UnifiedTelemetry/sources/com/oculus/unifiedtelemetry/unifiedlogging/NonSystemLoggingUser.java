package com.oculus.unifiedtelemetry.unifiedlogging;

import X.AbstractC0247Xu;
import X.QC;
import android.os.UserHandle;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.analytics2.LoggingUser;

@Dependencies({"_UL__ULSEP_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID"})
public class NonSystemLoggingUser extends LoggingUser {
    public static final String TAG = "NonSystemLoggingUser";
    public QC _UL_mInjectionContext;
    public final UserHandle mHandle;
    public volatile String mId = "0";

    @Inject
    public NonSystemLoggingUser(AbstractC0247Xu xu, @Assisted UserHandle userHandle) {
        super(false);
        this._UL_mInjectionContext = new QC(2, xu);
        this.mHandle = userHandle;
    }
}
