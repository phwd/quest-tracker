package com.facebook.debug.log;

import android.util.Log;
import com.facebook.common.logging.LoggingDelegate;
import com.facebook.infer.annotation.NullsafeStrict;

@NullsafeStrict
public class DefaultLoggingDelegate extends AbstractLoggingDelegate {
    public static final DefaultLoggingDelegate sInstance = new DefaultLoggingDelegate();

    public static LoggingDelegate getInstance() {
        return sInstance;
    }

    protected DefaultLoggingDelegate() {
    }

    @Override // com.facebook.debug.log.AbstractLoggingDelegate, com.facebook.common.logging.LoggingDelegate
    public void wtf(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // com.facebook.debug.log.AbstractLoggingDelegate, com.facebook.common.logging.LoggingDelegate
    public void wtf(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }
}
