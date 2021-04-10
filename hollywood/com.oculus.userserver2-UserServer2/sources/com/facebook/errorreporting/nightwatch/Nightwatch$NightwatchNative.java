package com.facebook.errorreporting.nightwatch;

import X.NJ;
import X.g6;
import android.annotation.SuppressLint;
import android.os.Build;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
public class Nightwatch$NightwatchNative {
    public static native int nRecordDataInNightWatch(long j, int i);

    public static native int nRecordDataInNightWatch_FAST_JNI(long j, int i);

    public static native int nRecordTickInNightWatch(long j, long j2, long j3, long j4);

    public static native int nRecordTickInNightWatch_FAST_JNI(long j, long j2, long j3, long j4);

    public static native int start(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, int i2, boolean z6, int i3, boolean z7, boolean z8);

    static {
        String str;
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                Class.forName("dalvik.annotation.optimization.CriticalNative");
            } catch (ClassNotFoundException unused) {
            }
        }
        if (Boolean.TRUE.equals(NJ.A01.get())) {
            g6.A00();
            str = "Redex: Unreachable code after no-return invoke";
        } else {
            str = "trying to load nightwatch before nightwatch starts!";
        }
        throw new RuntimeException(str);
    }
}
