package com.oculus.mobileconfig.init;

import X.EnumC02060Sk;
import com.facebook.inject.RequiresBinding;
import com.oculus.alpenglow.constants.Constants;

@RequiresBinding
public class MobileConfigInitOptions {
    public final boolean mShouldFetchOnLogin = true;
    public final String mTagPrefix = Constants.TAG_PREFIX;
    public final EnumC02060Sk mUniverseType;

    public MobileConfigInitOptions(EnumC02060Sk r3) {
        this.mUniverseType = r3;
    }
}
