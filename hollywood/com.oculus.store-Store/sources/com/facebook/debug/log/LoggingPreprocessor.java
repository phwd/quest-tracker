package com.facebook.debug.log;

import com.facebook.common.logging.LoggingDelegate;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class LoggingPreprocessor implements LoggingDelegate {
    private static final String TAG = LoggingPreprocessor.class.getSimpleName();
    private LoggingDelegate mDownstreamLogger = DefaultLoggingDelegate.getInstance();

    public abstract void processAndMaybeSend(int i, String str, String str2, @Nullable Throwable th, boolean z);

    public void setDownstreamLogger(LoggingDelegate logger) {
        this.mDownstreamLogger = logger;
    }

    /* access modifiers changed from: protected */
    public void sendDownstream(int priority, String tag, String msg, @Nullable Throwable tr, boolean dotLog) {
        if (dotLog) {
            this.mDownstreamLogger.log(priority, tag, msg);
            return;
        }
        switch (priority) {
            case 2:
                if (tr != null) {
                    this.mDownstreamLogger.v(tag, msg, tr);
                    return;
                } else {
                    this.mDownstreamLogger.v(tag, msg);
                    return;
                }
            case 3:
                if (tr != null) {
                    this.mDownstreamLogger.d(tag, msg, tr);
                    return;
                } else {
                    this.mDownstreamLogger.d(tag, msg);
                    return;
                }
            case 4:
                if (tr != null) {
                    this.mDownstreamLogger.i(tag, msg, tr);
                    return;
                } else {
                    this.mDownstreamLogger.i(tag, msg);
                    return;
                }
            case 5:
                if (tr != null) {
                    this.mDownstreamLogger.w(tag, msg, tr);
                    return;
                } else {
                    this.mDownstreamLogger.w(tag, msg);
                    return;
                }
            case 6:
                if (tr != null) {
                    this.mDownstreamLogger.e(tag, msg, tr);
                    return;
                } else {
                    this.mDownstreamLogger.e(tag, msg);
                    return;
                }
            case 7:
                if (tr != null) {
                    this.mDownstreamLogger.wtf(tag, msg, tr);
                    return;
                } else {
                    this.mDownstreamLogger.wtf(tag, msg);
                    return;
                }
            default:
                this.mDownstreamLogger.e(TAG, "Unexpected log priority " + priority);
                return;
        }
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void setMinimumLoggingLevel(int level) {
        this.mDownstreamLogger.setMinimumLoggingLevel(level);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public int getMinimumLoggingLevel() {
        return this.mDownstreamLogger.getMinimumLoggingLevel();
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public boolean isLoggable(int level) {
        return this.mDownstreamLogger.isLoggable(level);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void v(String tag, String msg) {
        processAndMaybeSend(2, tag, msg, null, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void v(String tag, String msg, Throwable tr) {
        processAndMaybeSend(2, tag, msg, tr, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void d(String tag, String msg) {
        processAndMaybeSend(3, tag, msg, null, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void d(String tag, String msg, Throwable tr) {
        processAndMaybeSend(3, tag, msg, tr, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void i(String tag, String msg) {
        processAndMaybeSend(4, tag, msg, null, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void i(String tag, String msg, Throwable tr) {
        processAndMaybeSend(4, tag, msg, tr, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void w(String tag, String msg) {
        processAndMaybeSend(5, tag, msg, null, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void w(String tag, String msg, Throwable tr) {
        processAndMaybeSend(5, tag, msg, tr, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void e(String tag, String msg) {
        processAndMaybeSend(6, tag, msg, null, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void e(String tag, String msg, Throwable tr) {
        processAndMaybeSend(6, tag, msg, tr, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void wtf(String tag, String msg) {
        processAndMaybeSend(7, tag, msg, null, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void wtf(String tag, String msg, Throwable tr) {
        processAndMaybeSend(7, tag, msg, tr, false);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void log(int priority, String tag, String msg) {
        processAndMaybeSend(priority, tag, msg, null, true);
    }
}
