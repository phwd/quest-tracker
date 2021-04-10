package com.oculus.library.utils;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import android.content.Context;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.userserver.api.sharing.SharingManager;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class AppSharingUtils {
    public AnonymousClass0QC _UL_mInjectionContext;
    public final SharingManager mSharingManager;

    @Inject
    public AppSharingUtils(AbstractC06640p5 r4) {
        AnonymousClass0QC r2 = new AnonymousClass0QC(1, r4);
        this._UL_mInjectionContext = r2;
        this.mSharingManager = new SharingManager((Context) AnonymousClass0J2.A03(0, 294, r2));
    }
}
