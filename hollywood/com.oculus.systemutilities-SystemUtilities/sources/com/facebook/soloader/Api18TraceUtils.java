package com.facebook.soloader;

import android.annotation.TargetApi;
import android.os.Trace;

/* access modifiers changed from: package-private */
@TargetApi(18)
public class Api18TraceUtils {
    public static void beginTraceSection(String prefix, String middle, String suffix) {
        String sectionName = prefix + middle + suffix;
        if (sectionName.length() > 127 && middle != null) {
            sectionName = prefix + middle.substring(0, (127 - prefix.length()) - suffix.length()) + suffix;
        }
        Trace.beginSection(sectionName);
    }

    public static void endSection() {
        Trace.endSection();
    }
}
