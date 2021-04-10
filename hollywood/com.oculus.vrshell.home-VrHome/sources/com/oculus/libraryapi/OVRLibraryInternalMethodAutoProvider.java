package com.oculus.libraryapi;

import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

public class OVRLibraryInternalMethodAutoProvider extends AbstractProvider<OVRLibraryInternal> {
    @Override // javax.inject.Provider
    public OVRLibraryInternal get() {
        return OVRLibraryModule.provideOVRLibraryInternal(BundledAndroidModule.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD(this));
    }
}
