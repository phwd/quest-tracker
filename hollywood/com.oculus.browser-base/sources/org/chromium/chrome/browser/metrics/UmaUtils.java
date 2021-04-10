package org.chromium.chrome.browser.metrics;

import android.os.Process;
import android.os.SystemClock;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UmaUtils {

    /* renamed from: a  reason: collision with root package name */
    public static long f10697a;
    public static long b;
    public static long c;

    public static boolean a() {
        return c != 0;
    }

    public static boolean b() {
        return b != 0;
    }

    public static void c() {
        long j = b;
        if (j == 0 || j < c) {
            b = SystemClock.uptimeMillis();
        }
    }

    public static long getApplicationStartTime() {
        return f10697a;
    }

    public static long getProcessStartTime() {
        return Process.getStartUptimeMillis();
    }
}
