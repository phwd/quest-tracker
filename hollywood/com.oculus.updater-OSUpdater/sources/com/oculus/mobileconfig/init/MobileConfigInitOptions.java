package com.oculus.mobileconfig.init;

import com.facebook.inject.RequiresBinding;
import com.facebook.mobileconfig.UniverseType;

@RequiresBinding
public class MobileConfigInitOptions {
    public final boolean mShouldFetchOnLogin;
    public final String mTagPrefix;
    public final UniverseType mUniverseType;

    public MobileConfigInitOptions(String str, boolean z, UniverseType universeType) {
        this.mTagPrefix = str;
        this.mShouldFetchOnLogin = z;
        this.mUniverseType = universeType;
    }
}
