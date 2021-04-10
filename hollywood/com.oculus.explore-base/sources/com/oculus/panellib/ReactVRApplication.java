package com.oculus.panellib;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import com.facebook.debug.log.BLog;
import com.facebook.jni.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.io.IOException;
import java.util.ArrayList;

public class ReactVRApplication extends Application {
    private static final long MAX_MODULE_CREATE_TIME_MS = 100;
    private static final String TAG = ReactVRApplication.class.getSimpleName();
    private static boolean mInitOnCreate = true;
    private static boolean mInitialized = false;
    private static ReactVRApplication mInstance;
    private static ArrayList<RCTBaseJavaModule> mModules = new ArrayList<>();
    private static boolean mModulesCreated = false;
    private static int mPreOnCreateBlock;
    private static long sPreOnCreateStart = System.nanoTime();

    public ReactVRApplication(String buildType) {
        if (buildType == "debug") {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }
        boolean isSystraceEnabled = buildType != "release" || SystemProperties.getString("debug.reactvr.systrace.enable", "0").equals("1");
        Systrace.enable(isSystraceEnabled);
        Systrace.enableSystemTracing(isSystraceEnabled);
        mPreOnCreateBlock = Systrace.beginBlock(TAG, "preOnCreate", sPreOnCreateStart);
        BLog.setLogLevel(buildType == "debug" ? 2 : 4);
        mInstance = this;
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            SoLoader.init(base, 2);
        } catch (IOException ex) {
            Log.e(TAG, "IOException during init", ex);
        }
    }

    public static void setInitOnCreate(boolean initOnCreate) {
        mInitOnCreate = initOnCreate;
    }

    public void onCreate() {
        Systrace.end(mPreOnCreateBlock);
        if (mInitOnCreate) {
            init();
        } else {
            Log.i(TAG, "Skipping initPanelApp on creation.");
        }
    }

    public static void onServiceDestroy() {
    }

    public static void initPanelApp() {
        if (mInstance != null) {
            mInstance.init();
        } else {
            Log.w(TAG, "Calling init before application instance is created");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0048, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0049, code lost:
        r4 = r2;
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004d, code lost:
        if (r4 != null) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x005b, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x005f, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x007a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x007b, code lost:
        r4.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x007f, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0083, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0084, code lost:
        r3 = null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x005b A[ExcHandler: all (th java.lang.Throwable)] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void init() {
        /*
        // Method dump skipped, instructions count: 134
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.ReactVRApplication.init():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0057, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0058, code lost:
        r6 = r5;
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006c, code lost:
        r5 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.lang.String getProcessName(android.content.Context r9) {
        /*
        // Method dump skipped, instructions count: 110
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.ReactVRApplication.getProcessName(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0086, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0087, code lost:
        r13 = r12;
        r12 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a6, code lost:
        r12 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static <T> T createModule(java.lang.Class<T> r19) {
        /*
        // Method dump skipped, instructions count: 168
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.ReactVRApplication.createModule(java.lang.Class):java.lang.Object");
    }

    @DoNotStrip
    public static Object[] getModules() {
        Object[] array;
        synchronized (mModules) {
            if (!mModulesCreated) {
                try {
                    mModules.wait();
                } catch (InterruptedException e) {
                    Log.w(TAG, "getModules - thread execution has been interrupted");
                }
            }
            array = mModules.toArray();
        }
        return array;
    }

    /* access modifiers changed from: protected */
    public void createApplication() {
    }

    /* access modifiers changed from: protected */
    public void createModules() {
    }
}
