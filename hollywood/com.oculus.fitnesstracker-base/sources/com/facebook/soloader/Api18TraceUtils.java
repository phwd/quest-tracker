package com.facebook.soloader;

import android.annotation.TargetApi;
import android.os.Trace;

/* access modifiers changed from: package-private */
@TargetApi(18)
public final class Api18TraceUtils {
    public static void beginTraceSection(String str, String str2, String str3) {
        String str4 = str + str2 + str3;
        if (str4.length() > 127 && str2 != null) {
            int length = (127 - str.length()) - str3.length();
            str4 = str + str2.substring(0, length) + str3;
        }
        Trace.beginSection(str4);
    }
}
