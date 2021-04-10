package com.facebook.systrace;

import android.os.Build;
import com.facebook.androidinternals.android.os.TraceInternal;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class SystraceEnabledDetector {
    public static boolean computeIsTracing() {
        if (Build.VERSION.SDK_INT >= 18) {
            return TraceInternal.isTagEnabled(TraceInternal.TRACE_TAG_APP);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return TraceConfigJB.isTracing();
        }
        return false;
    }

    public static boolean shouldLoadTracingLibrary() {
        return computeIsTracing() || TraceConfig.computeTraceTags() != 0;
    }

    public static String getMyCommandLine() {
        try {
            FileReader fw = new FileReader("/proc/self/cmdline");
            try {
                BufferedReader br = new BufferedReader(fw);
                try {
                    String res = br.readLine();
                    int indexOfNullTerminator = res.indexOf(0);
                    if (indexOfNullTerminator >= 0) {
                        res = res.substring(0, indexOfNullTerminator);
                    }
                    br.close();
                    fw.close();
                    return res;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
                throw th;
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
