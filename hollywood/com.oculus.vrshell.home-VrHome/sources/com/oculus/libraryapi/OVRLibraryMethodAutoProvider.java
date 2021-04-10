package com.oculus.libraryapi;

import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

public class OVRLibraryMethodAutoProvider extends AbstractProvider<OVRLibrary> {
    @Override // javax.inject.Provider
    public OVRLibrary get() {
        return OVRLibraryModule.provideOVRLibrary(BundledAndroidModule.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD(this));
    }
}
