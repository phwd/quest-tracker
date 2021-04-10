package com.facebook.debug.log;

import com.facebook.common.logging.LoggingDelegate;

public class DefaultLoggingDelegate extends AbstractLoggingDelegate {
    public static final DefaultLoggingDelegate sInstance = new DefaultLoggingDelegate();

    public static LoggingDelegate getInstance() {
        return sInstance;
    }

    protected DefaultLoggingDelegate() {
    }
}
