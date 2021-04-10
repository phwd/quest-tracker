package com.oculus.mobileconfig.init;

import X.RX;
import com.facebook.inject.RequiresBinding;

@RequiresBinding
public class MobileConfigInitOptions {
    public final boolean mShouldFetchOnLogin = true;
    public final String mTagPrefix = "";
    public final RX mUniverseType;

    public MobileConfigInitOptions(RX rx) {
        this.mUniverseType = rx;
    }
}
