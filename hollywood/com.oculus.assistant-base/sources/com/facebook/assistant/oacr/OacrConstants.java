package com.facebook.assistant.oacr;

import X.AnonymousClass08;
import android.os.Build;

public class OacrConstants {
    public static final String AUTO_SPEECH_DOMAIN = "";
    public static final String DEVICE_NAME;
    public static final String DEVICE_TYPE;
    public static final String OACR_PROTOCOL_NAME = "OACR";
    public static final String UNIQUE_DEVICE_ID = Build.SERIAL;

    static {
        String str = Build.HARDWARE;
        DEVICE_TYPE = str;
        DEVICE_NAME = AnonymousClass08.A05(str, " - ", Build.DISPLAY);
    }
}
