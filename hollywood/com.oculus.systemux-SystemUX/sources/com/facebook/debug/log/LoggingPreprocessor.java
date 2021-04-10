package com.facebook.debug.log;

import com.facebook.common.logging.LoggingDelegate;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class LoggingPreprocessor implements LoggingDelegate {
    private static final String TAG = "LoggingPreprocessor";
    private LoggingDelegate mDownstreamLogger = DefaultLoggingDelegate.getInstance();

    public abstract void processAndMaybeSend(int i, String str, String str2, @Nullable Throwable th, boolean z);

    public void setDownstreamLogger(LoggingDelegate loggingDelegate) {
        this.mDownstreamLogger = loggingDelegate;
    }

    /* access modifiers changed from: protected */
    public void sendDownstream(int i, String str, String str2, @Nullable Throwable th, boolean z) {
        if (z) {
            this.mDownstreamLogger.log(i, str, str2);
            return;
        }
        switch (i) {
            case 2:
                if (th != null) {
                    this.mDownstreamLogger.v(str, str2, th);
                    return;
                } else {
                    this.mDownstreamLogger.v(str, str2);
                    return;
                }
            case 3:
                if (th != null) {
                    this.mDownstreamLogger.d(str, str2, th);
                    return;
                } else {
                    this.mDownstreamLogger.d(str, str2);
                    return;
                }
            case 4:
                if (th != null) {
                    this.mDownstreamLogger.i(str, str2, th);
                    return;
                } else {
                    this.mDownstreamLogger.i(str, str2);
                    return;
                }
            case 5:
                if (th != null) {
                    this.mDownstreamLogger.w(str, str2, th);
                    return;
                } else {
                    this.mDownstreamLogger.w(str, str2);
                    return;
                }
            case 6:
                if (th != null) {
                    this.mDownstreamLogger.e(str, str2, th);
                    return;
                } else {
                    this.mDownstreamLogger.e(str, str2);
                    return;
                }
            case 7:
                if (th != null) {
                    this.mDownstreamLogger.wtf(str, str2, th);
                    return;
                } else {
                    this.mDownstreamLogger.wtf(str, str2);
                    return;
                }
            default:
                LoggingDelegate loggingDelegate = this.mDownstreamLogger;
                String str3 = TAG;
                loggingDelegate.e(str3, "Unexpected log priority " + i);
                return;
        }
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void setMinimumLoggingLevel(int i) {
        this.mDownstreamLogger.setMinimumLoggingLevel(i);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public int getMinimumLoggingLevel() {
        return this.mDownstreamLogger.getMinimumLoggingLevel();
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public boolean isLoggable(int i) {
        return this.mDownstreamLogger.isLoggable(i);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void v(String str, String str2) {
        processAndMaybeSend(2, str, str2, null, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void v(String str, String str2, Throwable th) {
        processAndMaybeSend(2, str, str2, th, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void d(String str, String str2) {
        processAndMaybeSend(3, str, str2, null, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void d(String str, String str2, Throwable th) {
        processAndMaybeSend(3, str, str2, th, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void i(String str, String str2) {
        processAndMaybeSend(4, str, str2, null, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void i(String str, String str2, Throwable th) {
        processAndMaybeSend(4, str, str2, th, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void w(String str, String str2) {
        processAndMaybeSend(5, str, str2, null, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void w(String str, String str2, Throwable th) {
        processAndMaybeSend(5, str, str2, th, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void e(String str, String str2) {
        processAndMaybeSend(6, str, str2, null, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void e(String str, String str2, Throwable th) {
        processAndMaybeSend(6, str, str2, th, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void wtf(String str, String str2) {
        processAndMaybeSend(7, str, str2, null, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void wtf(String str, String str2, Throwable th) {
        processAndMaybeSend(7, str, str2, th, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void log(int i, String str, String str2) {
        processAndMaybeSend(i, str, str2, null, true);
    }
}
