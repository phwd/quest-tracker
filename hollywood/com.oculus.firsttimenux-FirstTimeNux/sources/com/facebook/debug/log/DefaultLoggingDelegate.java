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
    public void wtf(String tag, String msg) {
        Log.e(tag, msg);
    }

    @Override // com.facebook.debug.log.AbstractLoggingDelegate, com.facebook.common.logging.LoggingDelegate
    public void wtf(String tag, String msg, Throwable tr) {
        Log.e(tag, msg, tr);
    }
}
