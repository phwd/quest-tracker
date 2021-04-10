package com.facebook.debug.log;

import android.util.Log;
import com.facebook.common.logging.LoggingDelegate;

public abstract class AbstractLoggingDelegate implements LoggingDelegate {
    private int mMinimumLoggingLevel;

    @Override // com.facebook.common.logging.LoggingDelegate
    public void setMinimumLoggingLevel(int i) {
        this.mMinimumLoggingLevel = i;
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public boolean isLoggable(int i) {
        return this.mMinimumLoggingLevel <= i;
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void w(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void e(String str, String str2) {
        Log.e(str, str2);
    }
}
