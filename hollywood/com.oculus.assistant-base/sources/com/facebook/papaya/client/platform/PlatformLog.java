package com.facebook.papaya.client.platform;

import X.KJ;
import com.facebook.common.stringformat.StringFormatUtil;

public final class PlatformLog {
    public static PlatformLogHandlerImpl A00;

    static {
        KJ.A05("papaya", 0);
    }

    public static void A00(Class cls, String str, Object... objArr) {
        synchronized (PlatformLog.class) {
            if (A00 == null) {
                A00 = new PlatformLogHandlerImpl();
            }
        }
        PlatformLogHandlerImpl.nativeLog(1, cls.getSimpleName(), new StringBuilder(StringFormatUtil.formatStrLocaleSafe(str, objArr)).toString());
    }
}
