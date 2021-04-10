package com.oculus.panellib;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.os.StrictMode;
import android.util.Log;
import com.facebook.debug.log.BLog;
import com.facebook.jni.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;
import com.oculus.fitnesstracker.BuildConfig;
import com.oculus.fitnesstracker.database.FitnessTrackerMoveContract;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ReactVRApplication extends Application {
    private static final long MAX_MODULE_CREATE_TIME_MS = 100;
    private static final String TAG = "ReactVRApplication";
    private static boolean mInitOnCreate = true;
    private static boolean mInitialized = false;
    private static ReactVRApplication mInstance;
    private static ArrayList<RCTBaseJavaModule> mModules = new ArrayList<>();
    private static boolean mModulesCreated = false;
    private static int mPreOnCreateBlock;
    private static long sPreOnCreateStart = System.nanoTime();

    public static void onServiceDestroy() {
    }

    /* access modifiers changed from: protected */
    public void createApplication() {
    }

    /* access modifiers changed from: protected */
    public void createModules() {
    }

    public ReactVRApplication(String str) {
        if (str == "debug") {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }
        boolean z = str != BuildConfig.BUILD_TYPE || SystemProperties.getString("debug.reactvr.systrace.enable", "0").equals("1");
        Systrace.enable(z);
        Systrace.enableSystemTracing(z);
        mPreOnCreateBlock = Systrace.beginBlock(TAG, "preOnCreate", sPreOnCreateStart);
        BLog.setLogLevel(str == "debug" ? 2 : 4);
        mInstance = this;
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        try {
            SoLoader.init(context, 2);
        } catch (IOException e) {
            Log.e(TAG, "IOException during init", e);
        }
    }

    public static void setInitOnCreate(boolean z) {
        mInitOnCreate = z;
    }

    public void onCreate() {
        Systrace.end(mPreOnCreateBlock);
        if (mInitOnCreate) {
            init();
        } else {
            Log.i(TAG, "Skipping initPanelApp on creation.");
        }
    }

    public static void initPanelApp() {
        ReactVRApplication reactVRApplication = mInstance;
        if (reactVRApplication != null) {
            reactVRApplication.init();
        } else {
            Log.w(TAG, "Calling init before application instance is created");
        }
    }

    private void init() {
        if (!mInitialized) {
            try {
                QplPointRange qplPointRange = new QplPointRange(Qpl.QPL_MARKER_INIT, "app_create");
                try {
                    super.onCreate();
                    ThreadExecutor.getInstance().execute(new Callable<Void>() {
                        /* class com.oculus.panellib.ReactVRApplication.AnonymousClass1 */

                        @Override // java.util.concurrent.Callable
                        public Void call() {
                            synchronized (ReactVRApplication.mModules) {
                                Log.i(ReactVRApplication.TAG, "Started creating native Java modules...");
                                ReactVRApplication.this.createModules();
                                boolean unused = ReactVRApplication.mModulesCreated = true;
                                String str = ReactVRApplication.TAG;
                                Log.i(str, "Created native Java modules: " + ReactVRApplication.mModules.size());
                                ReactVRApplication.mModules.notify();
                            }
                            return null;
                        }
                    }, "createModules");
                    SystraceBlock systraceBlock = new SystraceBlock(TAG, "createApplication");
                    try {
                        createApplication();
                        systraceBlock.close();
                        qplPointRange.close();
                        return;
                    } catch (Throwable unused) {
                    }
                } catch (Throwable unused2) {
                }
            } finally {
                mInitialized = true;
            }
        } else {
            return;
        }
        throw th;
        throw th;
    }

    protected static String getProcessName(Context context) {
        SystraceBlock systraceBlock = new SystraceBlock(TAG, "getProcessName");
        try {
            int myPid = Process.myPid();
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(FitnessTrackerMoveContract.Session.ACTIVITY)).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.pid == myPid) {
                        String str = runningAppProcessInfo.processName;
                        systraceBlock.close();
                        return str;
                    }
                }
            }
            systraceBlock.close();
            return null;
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|5|6|7|10|(1:12)|13|14|15|(1:(1:18))) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0025, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0027, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        android.util.Log.e(com.oculus.panellib.ReactVRApplication.TAG, java.lang.String.format("Failed to instantiate Java module class %s.", r2), r7);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0029 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static <T> T createModule(java.lang.Class<T> r11) {
        /*
        // Method dump skipped, instructions count: 118
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
                } catch (InterruptedException unused) {
                    Log.w(TAG, "getModules - thread execution has been interrupted");
                }
            }
            array = mModules.toArray();
        }
        return array;
    }
}
