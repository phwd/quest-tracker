package com.oculus.libraryapi;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class OVRLibraryMethodAutoProvider extends AbstractProvider<OVRLibrary> {
    @Override // javax.inject.Provider
    public OVRLibrary get() {
        return OVRLibraryModule.provideOVRLibrary(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this));
    }
}
