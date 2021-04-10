package com.facebook.debug.log;

import android.util.Log;
import com.facebook.common.logging.LoggingDelegate;

public abstract class AbstractLoggingDelegate implements LoggingDelegate {
    private int mMinimumLoggingLevel;

    @Override // com.facebook.common.logging.LoggingDelegate
    public void setMinimumLoggingLevel(int level) {
        this.mMinimumLoggingLevel = level;
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public int getMinimumLoggingLevel() {
        return this.mMinimumLoggingLevel;
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public boolean isLoggable(int level) {
        return this.mMinimumLoggingLevel <= level;
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void v(String tag, String msg) {
        Log.v(tag, msg);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void d(String tag, String msg, Throwable tr) {
        Log.d(tag, msg, tr);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void i(String tag, String msg, Throwable tr) {
        Log.i(tag, msg, tr);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void w(String tag, String msg) {
        Log.w(tag, msg);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void w(String tag, String msg, Throwable tr) {
        Log.w(tag, msg, tr);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void e(String tag, String msg, Throwable tr) {
        Log.e(tag, msg, tr);
    }
}
