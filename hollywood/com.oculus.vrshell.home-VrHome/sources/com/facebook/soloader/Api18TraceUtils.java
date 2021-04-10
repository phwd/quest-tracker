package com.facebook.soloader;

import android.annotation.TargetApi;
import android.os.Trace;
import com.oculus.os.Version;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@TargetApi(Version.VERSION_18)
public class Api18TraceUtils {
    public static void beginTraceSection(String prefix, @Nullable String middle, String suffix) {
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
