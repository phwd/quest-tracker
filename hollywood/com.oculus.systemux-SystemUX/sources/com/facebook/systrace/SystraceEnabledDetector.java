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
            FileReader fileReader = new FileReader("/proc/self/cmdline");
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                try {
                    String readLine = bufferedReader.readLine();
                    int indexOf = readLine.indexOf(0);
                    if (indexOf >= 0) {
                        readLine = readLine.substring(0, indexOf);
                    }
                    bufferedReader.close();
                    fileReader.close();
                    return readLine;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
                throw th;
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
