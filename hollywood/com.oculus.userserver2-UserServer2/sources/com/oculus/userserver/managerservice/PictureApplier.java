package com.oculus.userserver.managerservice;

import X.Om;
import X.SZ;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_userserver_managerservice_OculusUserManagerImpl_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_userserver_managerservice_UserManagerAccessor_ULSEP_BINDING_ID", "_UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_userserver_http_NoCertPinning_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_userserver_managerservice_BitmapFactoryAccessor_ULSEP_BINDING_ID"})
public class PictureApplier {
    public static final String TAG = "PictureApplier";
    public Om _UL_mInjectionContext;

    @Inject
    public PictureApplier(SZ sz) {
        this._UL_mInjectionContext = new Om(0, sz);
    }
}
