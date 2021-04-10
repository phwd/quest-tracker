package com.oculus.crashreporter;

import android.os.Handler;
import com.facebook.acra.ACRA;
import java.lang.ref.WeakReference;

public class JavascriptCrashReporter {
    public static final String TAG = "JavascriptCrashReporter";
    public static WeakReference<Handler> mHandler;

    public static native void nativeInit();

    public static void shutdown() {
        mHandler = null;
    }

    public static void init(Handler handler) {
        if (ACRA.mConfig != null) {
            mHandler = new WeakReference<>(handler);
            return;
        }
        throw new RuntimeException("ACRA hasn't been initialized, and is needed by JavascriptCrashReporter to work properly.");
    }

    public static boolean reportJSCrash(final String str) {
        Handler handler = mHandler.get();
        if (handler == null) {
            return false;
        }
        handler.post(new Runnable() {
            /* class com.oculus.crashreporter.JavascriptCrashReporter.AnonymousClass1 */

            public void run() {
                throw new ReactNativeException(str);
            }
        });
        return true;
    }

    public static class ReactNativeException extends RuntimeException {
        public ReactNativeException(String str) {
            super(str);
        }
    }

    static {
        nativeInit();
    }
}
