package com.oculus.userserver.managerservice;

import X.Om;
import X.SZ;
import android.content.Context;
import android.os.UserHandle;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.authapi.OVRAuth;
import com.oculus.authapi.inject.CallerInfoProviderImpl;

@Dependencies({"_UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
public class DsatFetcherForUser {
    public Om _UL_mInjectionContext;

    public static class OVRAuthForUser extends OVRAuth {
        public final Context mContext;
        public final UserHandle mUserHandle;

        public OVRAuthForUser(Context context, CallerInfoProviderImpl callerInfoProviderImpl, UserHandle userHandle) {
            super(context, callerInfoProviderImpl);
            this.mUserHandle = userHandle;
            this.mContext = context;
        }
    }

    @Inject
    public DsatFetcherForUser(SZ sz) {
        this._UL_mInjectionContext = new Om(2, sz);
    }
}
