package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0O3  reason: invalid class name */
public final class AnonymousClass0O3 {
    @Nullable
    public static File A00;
    public static final Object A01 = new Object();
    public static final ThreadLocal<Boolean> A02 = new ThreadLocal<>();

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        if (r13 == false) goto L_0x0030;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(java.io.File r14, X.AnonymousClass0O0 r15) {
        /*
            java.lang.ThreadLocal<java.lang.Boolean> r1 = X.AnonymousClass0O3.A02
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            r1.set(r0)
            X.AnonymousClass0O3.A00 = r14
            java.lang.String r3 = r14.getCanonicalPath()     // Catch:{ IOException -> 0x005a }
            java.lang.String r1 = "libwatcher_binary.so"
            java.lang.Class<X.0cX> r2 = X.C03250cX.class
            monitor-enter(r2)     // Catch:{ IOException -> 0x005a }
            X.0cY r0 = X.C03250cX.A00     // Catch:{ all -> 0x0057 }
            if (r0 == 0) goto L_0x004f
            monitor-exit(r2)     // Catch:{ all -> 0x0057 }
            X.0cY r0 = X.C03250cX.A00
            java.lang.String r2 = r0.A3i(r1)
            if (r2 != 0) goto L_0x0027
            java.lang.String r1 = "Nightwatch"
            java.lang.String r0 = "Could not find watcher binary"
            X.AnonymousClass0NO.A08(r1, r0)
            return
        L_0x0027:
            boolean r13 = r15.A06
            boolean r0 = com.facebook.errorreporting.nightwatch.Nightwatch$NightwatchNative.A00
            if (r0 == 0) goto L_0x0030
            r14 = 1
            if (r13 != 0) goto L_0x0031
        L_0x0030:
            r14 = 0
        L_0x0031:
            boolean r4 = r15.A04
            boolean r5 = r15.A09
            boolean r6 = r15.A07
            boolean r7 = r15.A08
            boolean r8 = r15.A03
            int r9 = r15.A01
            int r10 = r15.A02
            boolean r11 = r15.A05
            int r12 = r15.A00
            com.facebook.errorreporting.nightwatch.Nightwatch$NightwatchNative.start(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            java.lang.Object r1 = X.AnonymousClass0O3.A01
            monitor-enter(r1)
            monitor-exit(r1)     // Catch:{ all -> 0x004b }
            goto L_0x004e
        L_0x004b:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x004b }
            goto L_0x0059
        L_0x004e:
            return
        L_0x004f:
            java.lang.String r1 = "NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate())."
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0057:
            r0 = move-exception
            monitor-exit(r2)
        L_0x0059:
            throw r0
        L_0x005a:
            r2 = move-exception
            java.lang.String r1 = "Nightwatch"
            java.lang.String r0 = "Error starting watcher"
            X.AnonymousClass0NO.A0B(r1, r0, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0O3.A00(java.io.File, X.0O0):void");
    }
}
