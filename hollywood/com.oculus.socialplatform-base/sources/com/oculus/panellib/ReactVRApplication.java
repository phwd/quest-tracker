package com.oculus.panellib;

import X.AnonymousClass0l0;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.facebook.jni.annotations.DoNotStrip;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.io.IOException;
import java.util.ArrayList;

public class ReactVRApplication extends Application {
    public static final long MAX_MODULE_CREATE_TIME_MS = 100;
    public static final String TAG = "ReactVRApplication";
    public static boolean mInitOnCreate = true;
    public static boolean mInitialized;
    public static ReactVRApplication mInstance;
    public static ArrayList<RCTBaseJavaModule> mModules = new ArrayList<>();
    public static boolean mModulesCreated;
    public static int mPreOnCreateBlock;
    public static long sPreOnCreateStart = System.nanoTime();

    public static void onServiceDestroy() {
    }

    public void createApplication() {
    }

    public void createModules() {
    }

    @DoNotStrip
    public static Object[] getModules() {
        Object[] array;
        synchronized (mModules) {
            if (!mModulesCreated) {
                try {
                    mModules.wait();
                } catch (InterruptedException unused) {
                    Log.w(TAG, "getModules - thread execution has been interrupted");
                }
            }
            array = mModules.toArray();
        }
        return array;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getProcessName(android.content.Context r5) {
        /*
            java.lang.String r1 = "ReactVRApplication"
            java.lang.String r0 = "getProcessName"
            com.oculus.panellib.SystraceBlock r4 = new com.oculus.panellib.SystraceBlock
            r4.<init>(r1, r0)
            int r3 = android.os.Process.myPid()     // Catch:{ all -> 0x0037 }
            java.lang.String r0 = "activity"
            java.lang.Object r0 = r5.getSystemService(r0)     // Catch:{ all -> 0x0037 }
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0     // Catch:{ all -> 0x0037 }
            java.util.List r0 = r0.getRunningAppProcesses()     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0032
            java.util.Iterator r2 = r0.iterator()     // Catch:{ all -> 0x0037 }
        L_0x001f:
            boolean r0 = r2.hasNext()     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0032
            java.lang.Object r1 = r2.next()     // Catch:{ all -> 0x0037 }
            android.app.ActivityManager$RunningAppProcessInfo r1 = (android.app.ActivityManager.RunningAppProcessInfo) r1     // Catch:{ all -> 0x0037 }
            int r0 = r1.pid     // Catch:{ all -> 0x0037 }
            if (r0 != r3) goto L_0x001f
            java.lang.String r0 = r1.processName     // Catch:{ all -> 0x0037 }
            goto L_0x0033
        L_0x0032:
            r0 = 0
        L_0x0033:
            r4.close()
            return r0
        L_0x0037:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r0 = move-exception
            r4.close()     // Catch:{ all -> 0x003d }
        L_0x003d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.ReactVRApplication.getProcessName(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0034, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0038, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void init() {
        /*
            r5 = this;
            boolean r0 = com.oculus.panellib.ReactVRApplication.mInitialized
            if (r0 != 0) goto L_0x003d
            r4 = 1
            r1 = 51707905(0x3150001, float:4.378717E-37)
            java.lang.String r0 = "app_create"
            com.oculus.panellib.QplPointRange r3 = new com.oculus.panellib.QplPointRange     // Catch:{ all -> 0x0039 }
            r3.<init>(r1, r0)     // Catch:{ all -> 0x0039 }
            super.onCreate()     // Catch:{ all -> 0x0032 }
            com.oculus.panellib.ThreadExecutor r2 = com.oculus.panellib.ThreadExecutor.getInstance()     // Catch:{ all -> 0x0032 }
            com.oculus.panellib.ReactVRApplication$1 r1 = new com.oculus.panellib.ReactVRApplication$1     // Catch:{ all -> 0x0032 }
            r1.<init>()     // Catch:{ all -> 0x0032 }
            java.lang.String r0 = "createModules"
            r2.execute(r1, r0)     // Catch:{ all -> 0x0032 }
            java.lang.String r2 = "ReactVRApplication"
            java.lang.String r1 = "createApplication"
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock     // Catch:{ all -> 0x0032 }
            r0.<init>(r2, r1)     // Catch:{ all -> 0x0032 }
            r0.close()     // Catch:{ all -> 0x0032 }
            r3.close()
            com.oculus.panellib.ReactVRApplication.mInitialized = r4
            return
        L_0x0032:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0034 }
        L_0x0034:
            r0 = move-exception
            r3.close()     // Catch:{ all -> 0x0038 }
        L_0x0038:
            throw r0
        L_0x0039:
            r0 = move-exception
            com.oculus.panellib.ReactVRApplication.mInitialized = r4
            throw r0
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.ReactVRApplication.init():void");
    }

    public static void initPanelApp() {
        ReactVRApplication reactVRApplication = mInstance;
        if (reactVRApplication != null) {
            reactVRApplication.init();
        } else {
            Log.w(TAG, "Calling init before application instance is created");
        }
    }

    public void onCreate() {
        Systrace.end(mPreOnCreateBlock);
        if (mInitOnCreate) {
            init();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002e, code lost:
        if (com.oculus.panellib.SystemProperties.getString("debug.reactvr.systrace.enable", "0").equals("1") != false) goto L_0x0030;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReactVRApplication(java.lang.String r6) {
        /*
            r5 = this;
            r5.<init>()
            java.lang.String r4 = "debug"
            if (r6 != r4) goto L_0x001b
            android.os.StrictMode$VmPolicy$Builder r0 = new android.os.StrictMode$VmPolicy$Builder
            r0.<init>()
            android.os.StrictMode$VmPolicy$Builder r0 = r0.detectAll()
            android.os.StrictMode$VmPolicy$Builder r0 = r0.penaltyLog()
            android.os.StrictMode$VmPolicy r0 = r0.build()
            android.os.StrictMode.setVmPolicy(r0)
        L_0x001b:
            java.lang.String r0 = "release"
            if (r6 != r0) goto L_0x0030
            java.lang.String r1 = "debug.reactvr.systrace.enable"
            java.lang.String r0 = "0"
            java.lang.String r1 = com.oculus.panellib.SystemProperties.getString(r1, r0)
            java.lang.String r0 = "1"
            boolean r1 = r1.equals(r0)
            r0 = 0
            if (r1 == 0) goto L_0x0031
        L_0x0030:
            r0 = 1
        L_0x0031:
            com.oculus.panellib.Systrace.enable(r0)
            com.oculus.panellib.Systrace.enableSystemTracing(r0)
            java.lang.String r3 = "ReactVRApplication"
            long r1 = com.oculus.panellib.ReactVRApplication.sPreOnCreateStart
            java.lang.String r0 = "preOnCreate"
            int r0 = com.oculus.panellib.Systrace.beginBlock(r3, r0, r1)
            com.oculus.panellib.ReactVRApplication.mPreOnCreateBlock = r0
            r0 = 4
            if (r6 != r4) goto L_0x0047
            r0 = 2
        L_0x0047:
            X.AnonymousClass0MD.A00(r0)
            com.oculus.panellib.ReactVRApplication.mInstance = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.ReactVRApplication.<init>(java.lang.String):void");
    }

    public static /* synthetic */ String access$100() {
        return TAG;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|10|(1:12)|13|14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0070, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0030, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        android.util.Log.e(com.oculus.panellib.ReactVRApplication.TAG, java.lang.String.format("Failed to instantiate Java module class %s.", r7), r8);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0023 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T createModule(java.lang.Class<T> r9) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.ReactVRApplication.createModule(java.lang.Class):java.lang.Object");
    }

    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        try {
            AnonymousClass0l0.A04(context, 2);
        } catch (IOException e) {
            Log.e(TAG, "IOException during init", e);
        }
    }

    public static void setInitOnCreate(boolean z) {
        mInitOnCreate = z;
    }
}
