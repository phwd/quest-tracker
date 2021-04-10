package com.facebook.errorreporting.nightwatch;

import android.annotation.SuppressLint;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
public class Nightwatch$NightwatchNative {
    public static final boolean A00;

    public static native int nRecordDataInNightWatch(long j, int i);

    public static native int nRecordDataInNightWatch_FAST_JNI(long j, int i);

    public static native int nRecordTickInNightWatch(long j, long j2, long j3, long j4);

    public static native int nRecordTickInNightWatch_FAST_JNI(long j, long j2, long j3, long j4);

    public static native int start(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, int i2, int i3, boolean z6, int i4, boolean z7, boolean z8, int i5);

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0025  */
    static {
        /*
            int r1 = android.os.Build.VERSION.SDK_INT
            r0 = 26
            if (r1 < r0) goto L_0x000c
            java.lang.String r0 = "dalvik.annotation.optimization.CriticalNative"
            java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x000c }
            goto L_0x000e
        L_0x000c:
            r0 = 0
            goto L_0x000f
        L_0x000e:
            r0 = 1
        L_0x000f:
            com.facebook.errorreporting.nightwatch.Nightwatch$NightwatchNative.A00 = r0
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            java.lang.ThreadLocal<java.lang.Boolean> r0 = X.AnonymousClass0N7.A02
            java.lang.Object r0 = r0.get()
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0025
            java.lang.String r0 = "fbnightwatch"
            X.AnonymousClass0lD.A01(r0)
            return
        L_0x0025:
            java.lang.String r1 = "trying to load nightwatch before nightwatch starts!"
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.errorreporting.nightwatch.Nightwatch$NightwatchNative.<clinit>():void");
    }
}
