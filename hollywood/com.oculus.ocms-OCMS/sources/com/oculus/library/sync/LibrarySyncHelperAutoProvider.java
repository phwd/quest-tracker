package com.oculus.library.sync;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class LibrarySyncHelperAutoProvider extends AbstractProvider<LibrarySyncHelper> {
    @Override // javax.inject.Provider
    public LibrarySyncHelper get() {
        return new LibrarySyncHelper(this, BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
