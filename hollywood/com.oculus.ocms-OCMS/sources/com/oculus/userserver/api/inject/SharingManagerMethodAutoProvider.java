package com.oculus.userserver.api.inject;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.userserver.api.sharing.SharingManager;

@Generated({"By: InjectorProcessor"})
public class SharingManagerMethodAutoProvider extends AbstractProvider<SharingManager> {
    @Override // javax.inject.Provider
    public SharingManager get() {
        return UserServerInjectorModule.provideSharingManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this));
    }
}
