package com.oculus.appmanager.patcher;

import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_appmanager_patcher_RsyncNativeLibrary_ULSEP_BINDING_ID"})
@ApplicationScoped
public class Patcher {
    public static volatile Patcher _UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_INSTANCE;
    public RsyncNativeLibrary mRsyncNativeLibrary;

    @Inject
    public Patcher(RsyncNativeLibrary rsyncNativeLibrary) {
        this.mRsyncNativeLibrary = rsyncNativeLibrary;
    }
}
