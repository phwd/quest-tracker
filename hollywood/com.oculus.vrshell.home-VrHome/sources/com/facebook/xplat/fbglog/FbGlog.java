package com.facebook.xplat.fbglog;

import com.facebook.common.build.config.BuildConfig;
import com.facebook.debug.log.BLog;
import com.facebook.debug.log.BLogLevelCallback;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;

@DoNotStrip
public class FbGlog {
    private static BLogLevelCallback sCallback;

    public static native void setLogLevel(int i);

    @DoNotStrip
    static synchronized void ensureSubscribedToBLogLevelChanges() {
        synchronized (FbGlog.class) {
            if (sCallback == null) {
                sCallback = new BLogLevelCallbackImpl();
                BLog.registerOnLogLevelChanged(sCallback);
                setLogLevel(BLog.getLogLevel());
            }
        }
    }

    static class BLogLevelCallbackImpl implements BLogLevelCallback {
        BLogLevelCallbackImpl() {
        }

        @Override // com.facebook.debug.log.BLogLevelCallback
        public void onLogLevelChanged(int newLevel) {
            FbGlog.setLogLevel(newLevel);
        }
    }

    static {
        NativeLoader.loadLibrary(BuildConfig.FB_URL_SCHEME);
    }
}
