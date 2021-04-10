package com.facebook.mobileconfig.impl;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigFactoryUtils {
    public static int getInstanceKey(boolean z) {
        return z ? 1 : 2;
    }
}
