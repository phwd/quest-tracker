package com.oculus.horizon.accountlinking.dropbox;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_accountlinking_dropbox_DropboxAPIMethods_ULSEP_BINDING_ID"})
public class DropboxAPIHelper {
    public static final String TAG = "DropboxAPIHelper";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final DropboxAPIMethods mDropboxAPIMethods;

    @Inject
    public DropboxAPIHelper(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mDropboxAPIMethods = (DropboxAPIMethods) AnonymousClass117.A00(426, r3);
    }
}
