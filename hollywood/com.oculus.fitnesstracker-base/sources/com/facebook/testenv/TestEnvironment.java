package com.facebook.testenv;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class TestEnvironment {
    private static volatile int sIsTest;

    private TestEnvironment() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isTest() {
        /*
            int r0 = com.facebook.testenv.TestEnvironment.sIsTest
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x000c
            int r0 = com.facebook.testenv.TestEnvironment.sIsTest
            if (r0 != r2) goto L_0x000b
            return r2
        L_0x000b:
            return r1
        L_0x000c:
            java.lang.Class<com.facebook.testenv.TestEnvironment> r0 = com.facebook.testenv.TestEnvironment.class
            monitor-enter(r0)
            int r3 = com.facebook.testenv.TestEnvironment.sIsTest     // Catch:{ all -> 0x0053 }
            if (r3 == 0) goto L_0x001a
            int r3 = com.facebook.testenv.TestEnvironment.sIsTest     // Catch:{ all -> 0x0053 }
            if (r3 != r2) goto L_0x0018
            r1 = 1
        L_0x0018:
            monitor-exit(r0)     // Catch:{ all -> 0x0053 }
            return r1
        L_0x001a:
            boolean r3 = com.facebook.common.build.BuildConstants.isInternalBuild()     // Catch:{ all -> 0x0053 }
            if (r3 == 0) goto L_0x0049
            java.lang.String r3 = "org.junit.Test"
            boolean r3 = hasClass(r3)     // Catch:{ all -> 0x0053 }
            if (r3 == 0) goto L_0x0049
            java.lang.String r3 = "io.selendroid.client.SelendroidDriver"
            boolean r3 = hasClass(r3)     // Catch:{ all -> 0x0053 }
            if (r3 != 0) goto L_0x003b
            java.lang.String r3 = "io.selendroid.server.UncaughtExceptionHandling"
            boolean r3 = hasClass(r3)     // Catch:{ all -> 0x0053 }
            if (r3 == 0) goto L_0x0039
            goto L_0x003b
        L_0x0039:
            r3 = 0
            goto L_0x003c
        L_0x003b:
            r3 = 1
        L_0x003c:
            if (r3 != 0) goto L_0x0049
            java.lang.String r3 = "com.facebook.cam.CamInstrumentationTestRunner"
            boolean r3 = hasClass(r3)     // Catch:{ all -> 0x0053 }
            if (r3 != 0) goto L_0x0049
            com.facebook.testenv.TestEnvironment.sIsTest = r2     // Catch:{ all -> 0x0053 }
            goto L_0x004c
        L_0x0049:
            r3 = -1
            com.facebook.testenv.TestEnvironment.sIsTest = r3     // Catch:{ all -> 0x0053 }
        L_0x004c:
            int r3 = com.facebook.testenv.TestEnvironment.sIsTest     // Catch:{ all -> 0x0053 }
            if (r3 != r2) goto L_0x0051
            r1 = 1
        L_0x0051:
            monitor-exit(r0)     // Catch:{ all -> 0x0053 }
            return r1
        L_0x0053:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.testenv.TestEnvironment.isTest():boolean");
    }

    private static boolean hasClass(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
