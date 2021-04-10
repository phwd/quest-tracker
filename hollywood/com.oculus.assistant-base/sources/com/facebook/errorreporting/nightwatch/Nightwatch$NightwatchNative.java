package com.facebook.errorreporting.nightwatch;

public class Nightwatch$NightwatchNative {
    public static final boolean CAN_USE_CRITICAL_NATIVE_METHODS;
    public static boolean sHasLinkedFastMethods;

    public static native int nRecordDataInNightWatch(long j, int i);

    public static native int nRecordDataInNightWatch_FAST_JNI(long j, int i);

    public static native int nRecordTickInNightWatch(long j, long j2, long j3, long j4);

    public static native int nRecordTickInNightWatch_FAST_JNI(long j, long j2, long j3, long j4);

    public static native int start(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, int i2, boolean z6, int i3, boolean z7, boolean z8);

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    static {
        /*
            int r1 = android.os.Build.VERSION.SDK_INT
            r0 = 26
            if (r1 < r0) goto L_0x001f
            java.lang.String r0 = "dalvik.annotation.optimization.CriticalNative"
            java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x000c }
            goto L_0x0021
        L_0x000c:
            r4 = move-exception
            java.lang.String r3 = "Nightwatch"
            java.lang.String r2 = "Critical Native could not be found. Using normal fastjni!"
            X.CM r1 = X.C0139Dd.A01
            r0 = 3
            boolean r0 = r1.A3Y(r0)
            if (r0 == 0) goto L_0x001f
            X.CM r0 = X.C0139Dd.A01
            r0.A1c(r3, r2, r4)
        L_0x001f:
            r0 = 0
            goto L_0x0022
        L_0x0021:
            r0 = 1
        L_0x0022:
            com.facebook.errorreporting.nightwatch.Nightwatch$NightwatchNative.CAN_USE_CRITICAL_NATIVE_METHODS = r0
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            java.lang.ThreadLocal r0 = X.Dy.A02
            java.lang.Object r0 = r0.get()
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0038
            java.lang.String r0 = "fbnightwatch"
            X.KV.A01(r0)
            return
        L_0x0038:
            java.lang.String r1 = "trying to load nightwatch before nightwatch starts!"
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.errorreporting.nightwatch.Nightwatch$NightwatchNative.<clinit>():void");
    }
}
