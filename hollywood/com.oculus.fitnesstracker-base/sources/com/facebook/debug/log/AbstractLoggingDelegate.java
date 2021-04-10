package com.facebook.debug.log;

import android.util.Log;
import com.facebook.common.logging.LoggingDelegate;
import com.facebook.infer.annotation.NullsafeStrict;

@NullsafeStrict
public abstract class AbstractLoggingDelegate implements LoggingDelegate {
    private int mMinimumLoggingLevel;

    @Override // com.facebook.common.logging.LoggingDelegate
    public final void setMinimumLoggingLevel(int i) {
        this.mMinimumLoggingLevel = i;
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public final boolean isLoggable(int i) {
        return this.mMinimumLoggingLevel <= i;
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public final void v(String str, String str2) {
        Log.v(str, str2);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public final void d(String str, String str2) {
        Log.d(str, str2);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public final void d(String str, String str2, Throwable th) {
        Log.d(str, str2, th);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public final void i(String str, String str2) {
        Log.i(str, str2);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public final void i(String str, String str2, Throwable th) {
        Log.i(str, str2, th);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public final void w(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public final void w(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public final void e(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public final void e(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }
}
