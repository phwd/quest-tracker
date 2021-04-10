package com.oculus.userserver.managerservice;

import X.Om;
import X.SZ;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_userserver_managerservice_SharingStore_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_userserver_managerservice_OculusUserBackend_ULSEP_BINDING_ID"})
public class SharingManagerImpl {
    public static final String TAG = "SharingManagerImpl";
    public Om _UL_mInjectionContext;

    @Inject
    public SharingManagerImpl(SZ sz) {
        this._UL_mInjectionContext = new Om(2, sz);
    }
}
