package com.facebook.debug.log;

import android.util.Log;
import com.facebook.common.logging.LoggingDelegate;
import com.facebook.infer.annotation.NullsafeStrict;

@NullsafeStrict
public abstract class AbstractLoggingDelegate implements LoggingDelegate {
    private int mMinimumLoggingLevel;

    @Override // com.facebook.common.logging.LoggingDelegate
    public abstract void wtf(String str, String str2);

    @Override // com.facebook.common.logging.LoggingDelegate
    public abstract void wtf(String str, String str2, Throwable th);

    @Override // com.facebook.common.logging.LoggingDelegate
    public void setMinimumLoggingLevel(int i) {
        this.mMinimumLoggingLevel = i;
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public int getMinimumLoggingLevel() {
        return this.mMinimumLoggingLevel;
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public boolean isLoggable(int i) {
        return this.mMinimumLoggingLevel <= i;
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void v(String str, String str2) {
        Log.v(str, str2);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void v(String str, String str2, Throwable th) {
        Log.v(str, str2, th);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void d(String str, String str2) {
        Log.d(str, str2);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void d(String str, String str2, Throwable th) {
        Log.d(str, str2, th);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void i(String str, String str2) {
        Log.i(str, str2);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void i(String str, String str2, Throwable th) {
        Log.i(str, str2, th);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void w(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void w(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void e(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void e(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void log(int i, String str, String str2) {
        Log.println(i, str, str2);
    }
}
