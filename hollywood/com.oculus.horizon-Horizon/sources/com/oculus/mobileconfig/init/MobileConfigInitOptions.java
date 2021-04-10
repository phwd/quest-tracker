package com.oculus.mobileconfig.init;

import X.AnonymousClass0Rb;
import com.facebook.inject.RequiresBinding;

@RequiresBinding
public class MobileConfigInitOptions {
    public final boolean mShouldFetchOnLogin = true;
    public final String mTagPrefix = "";
    public final AnonymousClass0Rb mUniverseType;

    public MobileConfigInitOptions(AnonymousClass0Rb r3) {
        this.mUniverseType = r3;
    }
}
