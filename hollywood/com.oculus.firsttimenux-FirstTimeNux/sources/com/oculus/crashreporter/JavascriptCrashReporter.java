package com.oculus.crashreporter;

import android.os.Handler;
import android.util.Log;
import com.facebook.acra.ACRA;
import java.lang.ref.WeakReference;

public class JavascriptCrashReporter {
    public static final String TAG = "JavascriptCrashReporter";
    private static WeakReference<Handler> mHandler = null;

    private static native void nativeInit();

    public static class ReactNativeException extends RuntimeException {
        public ReactNativeException(String jsStackTrace) {
            super(jsStackTrace);
        }
    }

    static {
        nativeInit();
    }

    public static void init(Handler handler) {
        if (mHandler != null) {
            Log.d(TAG, "JavascriptCrashReporter has been already initialized.");
        }
        if (ACRA.getConfig() == null) {
            throw new RuntimeException("ACRA hasn't been initialized, and is needed by JavascriptCrashReporter to work properly.");
        }
        mHandler = new WeakReference<>(handler);
    }

    public static void shutdown() {
        mHandler = null;
    }

    public static boolean reportJSCrash(final String stackTrace) {
        Handler handler = mHandler.get();
        if (handler == null) {
            Log.d(TAG, "Panel Service hasn't initialized JS crash reporter.");
            return false;
        }
        handler.post(new Runnable() {
            /* class com.oculus.crashreporter.JavascriptCrashReporter.AnonymousClass1 */

            public void run() {
                throw new ReactNativeException(stackTrace);
            }
        });
        return true;
    }
}
