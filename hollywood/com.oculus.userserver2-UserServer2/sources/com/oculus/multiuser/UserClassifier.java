package com.oculus.multiuser;

import X.Om;
import X.SZ;
import android.annotation.TargetApi;
import com.facebook.acra.util.minidump.MinidumpReader;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_android_os_internal_UserHandleInternal_ULSEP_BINDING_ID"})
@TargetApi(MinidumpReader.MODULE_LIST_OFFSET)
public class UserClassifier {
    public Om _UL_mInjectionContext;

    @Inject
    public UserClassifier(SZ sz) {
        this._UL_mInjectionContext = new Om(2, sz);
    }
}
