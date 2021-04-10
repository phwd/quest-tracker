package com.oculus.panellib;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import com.facebook.acra.CustomReportDataSupplier;
import com.facebook.breakpad.BreakpadManager;
import com.facebook.soloader.SoLoader;
import com.oculus.crashreporter.JavascriptCrashReporter;
import com.oculus.errorreporting.ErrorReporter;
import com.oculus.vrshell.reactvrpanellib.BuildConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import javax.inject.Provider;

public abstract class CrashReporting {
    private static final String TAG = "CrashReporting";
    private static boolean mJSInitialized = false;
    private static boolean mNativeJavaInitialized = false;

    public static void initialize(Context context, Handler handler, String str, String str2, String str3) {
        SystraceBlock systraceBlock = new SystraceBlock(TAG, "initialize");
        try {
            if (!mNativeJavaInitialized) {
                mNativeJavaInitialized = true;
                SystraceBlock systraceBlock2 = new SystraceBlock(TAG, "SoLoader");
                try {
                    SoLoader.init(context, 2);
                } catch (IOException e) {
                    try {
                        Log.e(TAG, "IOException during init", e);
                    } catch (Throwable unused) {
                    }
                }
                systraceBlock2.close();
                initializeNativeCrashReporting(context);
                initializeJavaCrashReporting(context, str, str2, str3);
            }
            if (!mJSInitialized) {
                mJSInitialized = true;
                initializeJSCrashReporting(handler);
            }
            systraceBlock.close();
            return;
            throw th;
            throw th;
        } catch (Throwable unused2) {
        }
    }

    public static void shutdown() {
        if (!BuildConfig.DEBUG) {
            JavascriptCrashReporter.shutdown();
        }
    }

    public static void initializeNativeCrashReporting(Context context) {
        if (!BuildConfig.DEBUG) {
            Log.d(TAG, "Breakpad for Native crashes enabled");
            if (!BreakpadManager.isActive()) {
                BreakpadManager.start(context);
                return;
            }
            return;
        }
        Log.w(TAG, "Breakpad for Native crashes disabled");
    }

    public static void initializeJavaCrashReporting(Context context, String str, String str2, String str3) {
        if (!BuildConfig.DEBUG) {
            Log.d(TAG, "ACRA/OculusCrashReporter for Java crashes enabled");
            String str4 = "https://www.facebook.com/mobile/generic_android_crash_logs/" + str2;
            if (Build.VERSION.SDK_INT < 23 || context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                File file = new File("/sdcard/crash_endpoint.test");
                if (file.exists()) {
                    byte[] bArr = new byte[256];
                    try {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        try {
                            int read = fileInputStream.read(bArr);
                            str4 = new String(bArr, 0, read) + "/" + str2;
                            fileInputStream.close();
                        } catch (Throwable unused) {
                        }
                    } catch (IOException unused2) {
                        Log.e(TAG, "ACRA/Failed to read from " + file.getAbsolutePath());
                    }
                }
            }
            Log.d(TAG, "ACRA/Initializing with endpoint " + str4);
            HashMap hashMap = new HashMap();
            hashMap.put("bundle_name", str);
            hashMap.put("device_id", Build.DEVICE);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("is_js_error", new CustomReportDataSupplier() {
                /* class com.oculus.panellib.CrashReporting.AnonymousClass1 */

                @Override // com.facebook.acra.CustomReportDataSupplier
                public String getCustomData(Throwable th) {
                    return String.valueOf(th instanceof JavascriptCrashReporter.ReactNativeException);
                }
            });
            ErrorReporter.initCrashReporting(context.getApplicationContext(), str2, str3, str4, null, new Provider<Boolean>() {
                /* class com.oculus.panellib.CrashReporting.AnonymousClass2 */

                @Override // javax.inject.Provider
                public Boolean get() {
                    return Boolean.FALSE;
                }
            }, hashMap, hashMap2);
            return;
        }
        Log.w(TAG, "ErrorReporter for Java crashes disabled");
        return;
        throw th;
    }

    public static void initializeJSCrashReporting(Handler handler) {
        if (handler == null || BuildConfig.DEBUG) {
            Log.w(TAG, "JavascriptCrashReporter is disabled");
            return;
        }
        Log.d(TAG, "JavascriptCrashReporter is enabled");
        JavascriptCrashReporter.init(handler);
    }
}
