package com.oculus.appmanager.patcher;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class PatcherAutoProvider extends AbstractProvider<Patcher> {
    @Override // javax.inject.Provider
    public Patcher get() {
        return new Patcher(RsyncNativeLibrary._UL__ULSEP_com_oculus_appmanager_patcher_RsyncNativeLibrary_ULSEP_ACCESS_METHOD(this));
    }
}
