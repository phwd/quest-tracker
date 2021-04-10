package com.oculus.systemutilities;

public abstract class BaseService {
    private static final String TAG = BaseService.class.getSimpleName();
    private static DebugIntentReceiver mDebugIntentReceiver = null;

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0037, code lost:
        r1 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void loadLibrary() {
        /*
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
            java.lang.String r1 = com.oculus.systemutilities.BaseService.TAG
            java.lang.String r2 = "loadLibrary"
            r0.<init>(r1, r2)
            r2 = 0
            java.lang.String r1 = "systemutilities"
            java.lang.System.loadLibrary(r1)     // Catch:{ Throwable -> 0x0020, all -> 0x0037 }
            if (r0 == 0) goto L_0x0016
            if (r2 == 0) goto L_0x001c
            r0.close()     // Catch:{ Throwable -> 0x0017 }
        L_0x0016:
            return
        L_0x0017:
            r1 = move-exception
            r2.addSuppressed(r1)
            goto L_0x0016
        L_0x001c:
            r0.close()
            goto L_0x0016
        L_0x0020:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0022 }
        L_0x0022:
            r2 = move-exception
            r4 = r2
            r2 = r1
            r1 = r4
        L_0x0026:
            if (r0 == 0) goto L_0x002d
            if (r2 == 0) goto L_0x0033
            r0.close()     // Catch:{ Throwable -> 0x002e }
        L_0x002d:
            throw r1
        L_0x002e:
            r3 = move-exception
            r2.addSuppressed(r3)
            goto L_0x002d
        L_0x0033:
            r0.close()
            goto L_0x002d
        L_0x0037:
            r1 = move-exception
            goto L_0x0026
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.systemutilities.BaseService.loadLibrary():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0041, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
        r1 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void initialize(android.content.Context r6, com.oculus.modules.codegen.SystemUtilitiesServiceModule.ServiceType r7, android.os.Handler r8) {
        /*
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
            java.lang.String r1 = com.oculus.systemutilities.BaseService.TAG
            java.lang.String r2 = "initialize"
            r0.<init>(r1, r2)
            r2 = 0
            java.lang.String r1 = com.oculus.systemutilities.BaseService.TAG     // Catch:{ Throwable -> 0x003f, all -> 0x0056 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x003f, all -> 0x0056 }
            r3.<init>()     // Catch:{ Throwable -> 0x003f, all -> 0x0056 }
            java.lang.String r4 = "onCreate service type "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x003f, all -> 0x0056 }
            java.lang.StringBuilder r3 = r3.append(r7)     // Catch:{ Throwable -> 0x003f, all -> 0x0056 }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x003f, all -> 0x0056 }
            android.util.Log.i(r1, r3)     // Catch:{ Throwable -> 0x003f, all -> 0x0056 }
            com.oculus.modules.SystemUtilitiesServiceModuleImpl.setServiceType(r7)     // Catch:{ Throwable -> 0x003f, all -> 0x0056 }
            com.oculus.modules.LibraryModuleImpl.registerLibraryChangeListener(r6)     // Catch:{ Throwable -> 0x003f, all -> 0x0056 }
            com.oculus.modules.LibraryModuleImpl.fetchInitialLibraryAsync(r6)     // Catch:{ Throwable -> 0x003f, all -> 0x0056 }
            com.oculus.localmedia.LocalMediaManager.init(r6)     // Catch:{ Throwable -> 0x003f, all -> 0x0056 }
            if (r0 == 0) goto L_0x0035
            if (r2 == 0) goto L_0x003b
            r0.close()     // Catch:{ Throwable -> 0x0036 }
        L_0x0035:
            return
        L_0x0036:
            r1 = move-exception
            r2.addSuppressed(r1)
            goto L_0x0035
        L_0x003b:
            r0.close()
            goto L_0x0035
        L_0x003f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0041 }
        L_0x0041:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L_0x0045:
            if (r0 == 0) goto L_0x004c
            if (r2 == 0) goto L_0x0052
            r0.close()     // Catch:{ Throwable -> 0x004d }
        L_0x004c:
            throw r1
        L_0x004d:
            r3 = move-exception
            r2.addSuppressed(r3)
            goto L_0x004c
        L_0x0052:
            r0.close()
            goto L_0x004c
        L_0x0056:
            r1 = move-exception
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.systemutilities.BaseService.initialize(android.content.Context, com.oculus.modules.codegen.SystemUtilitiesServiceModule$ServiceType, android.os.Handler):void");
    }

    public static void destroy() {
        if (mDebugIntentReceiver != null) {
            mDebugIntentReceiver.destroy();
        }
    }
}
