package com.facebook.papaya.client.platform;

import X.KJ;

public final class PlatformLogHandlerImpl {
    public static native void nativeLog(int i, String str, String str2);

    static {
        KJ.A05("papaya", 0);
    }
}
