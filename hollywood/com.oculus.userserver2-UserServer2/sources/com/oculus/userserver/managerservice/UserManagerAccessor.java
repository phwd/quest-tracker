package com.oculus.userserver.managerservice;

import X.Om;
import X.SZ;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
public class UserManagerAccessor {
    public Om _UL_mInjectionContext;

    @Inject
    public UserManagerAccessor(SZ sz) {
        this._UL_mInjectionContext = new Om(0, sz);
    }
}
