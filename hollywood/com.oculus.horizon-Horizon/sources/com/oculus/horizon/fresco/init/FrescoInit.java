package com.oculus.horizon.fresco.init;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.common.init.INeedInit;

@Dependencies({"_UL__ULSEP_okhttp3_OkHttpClient_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class FrescoInit implements INeedInit {
    public AnonymousClass0QC _UL_mInjectionContext;

    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x009d */
    @Override // com.oculus.common.init.INeedInit
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void init() {
        /*
        // Method dump skipped, instructions count: 251
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.fresco.init.FrescoInit.init():void");
    }

    @Inject
    public FrescoInit(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
