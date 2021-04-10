package com.facebook.mobileconfig.impl;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigFactoryUtils {
    protected static final int SESSIONLESS_INSTANCE_KEY = 1;
    protected static final int SESSION_BASED_INSTANCE_KEY = 2;

    public static int getInstanceKey(boolean z) {
        return z ? 1 : 2;
    }
}
