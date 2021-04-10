package com.oculus.vrshell.home;

public abstract class BaseService {
    private static final String TAG = BaseService.class.getSimpleName();
    private static DebugIntentReceiver mDebugIntentReceiver = null;

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0035, code lost:
        r1 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void loadLibrary() {
        /*
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
            java.lang.String r1 = "System.loadLibrary (home)"
            r0.<init>(r1)
            r2 = 0
            java.lang.String r1 = "home"
            java.lang.System.loadLibrary(r1)     // Catch:{ Throwable -> 0x001e, all -> 0x0035 }
            if (r0 == 0) goto L_0x0014
            if (r2 == 0) goto L_0x001a
            r0.close()     // Catch:{ Throwable -> 0x0015 }
        L_0x0014:
            return
        L_0x0015:
            r1 = move-exception
            r2.addSuppressed(r1)
            goto L_0x0014
        L_0x001a:
            r0.close()
            goto L_0x0014
        L_0x001e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0020 }
        L_0x0020:
            r2 = move-exception
            r4 = r2
            r2 = r1
            r1 = r4
        L_0x0024:
            if (r0 == 0) goto L_0x002b
            if (r2 == 0) goto L_0x0031
            r0.close()     // Catch:{ Throwable -> 0x002c }
        L_0x002b:
            throw r1
        L_0x002c:
            r3 = move-exception
            r2.addSuppressed(r3)
            goto L_0x002b
        L_0x0031:
            r0.close()
            goto L_0x002b
        L_0x0035:
            r1 = move-exception
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.home.BaseService.loadLibrary():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0069, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006a, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007e, code lost:
        r1 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void initialize(android.content.Context r6, com.oculus.modules.codegen.ServiceModule.ServiceType r7, android.os.Handler r8) {
        /*
        // Method dump skipped, instructions count: 128
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.home.BaseService.initialize(android.content.Context, com.oculus.modules.codegen.ServiceModule$ServiceType, android.os.Handler):void");
    }

    public static void destroy() {
        if (mDebugIntentReceiver != null) {
            mDebugIntentReceiver.destroy();
        }
    }
}
