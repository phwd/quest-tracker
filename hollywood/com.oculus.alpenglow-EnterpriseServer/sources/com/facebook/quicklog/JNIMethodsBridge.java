package com.facebook.quicklog;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class JNIMethodsBridge {
    @DoNotStrip
    public static void markerPoint(int i, int i2, String str, String[] strArr, @EventLevel int i3, long j, boolean z) {
    }

    @DoNotStrip
    public static void markerPoint(int i, int i2, String str, @EventLevel int i3, long j) {
    }

    @DoNotStrip
    public static void markerPoint(int i, int i2, String str, String str2, @EventLevel int i3, long j) {
    }
}
