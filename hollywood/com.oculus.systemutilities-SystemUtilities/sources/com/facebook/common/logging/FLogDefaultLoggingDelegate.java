package com.facebook.common.logging;

import android.util.Log;
import com.facebook.common.build.config.BuildConfig;
import java.io.PrintWriter;
import java.io.StringWriter;

public class FLogDefaultLoggingDelegate implements LoggingDelegate {
    public static final FLogDefaultLoggingDelegate sInstance = new FLogDefaultLoggingDelegate();
    private String mApplicationTag = BuildConfig.VERSION_NAME;
    private int mMinimumLoggingLevel = 5;

    public static FLogDefaultLoggingDelegate getInstance() {
        return sInstance;
    }

    private FLogDefaultLoggingDelegate() {
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void setMinimumLoggingLevel(int level) {
        this.mMinimumLoggingLevel = level;
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public boolean isLoggable(int level) {
        return this.mMinimumLoggingLevel <= level;
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void v(String tag, String msg) {
        println(2, tag, msg);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void d(String tag, String msg) {
        println(3, tag, msg);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void i(String tag, String msg) {
        println(4, tag, msg);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void i(String tag, String msg, Throwable tr) {
        println(4, tag, msg, tr);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void w(String tag, String msg) {
        println(5, tag, msg);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void w(String tag, String msg, Throwable tr) {
        println(5, tag, msg, tr);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void e(String tag, String msg) {
        println(6, tag, msg);
    }

    @Override // com.facebook.common.logging.LoggingDelegate
    public void e(String tag, String msg, Throwable tr) {
        println(6, tag, msg, tr);
    }

    private void println(int priority, String tag, String msg) {
        Log.println(priority, prefixTag(tag), msg);
    }

    private void println(int priority, String tag, String msg, Throwable tr) {
        Log.println(priority, prefixTag(tag), getMsg(msg, tr));
    }

    private String prefixTag(String tag) {
        if (this.mApplicationTag != null) {
            return this.mApplicationTag + ":" + tag;
        }
        return tag;
    }

    private static String getMsg(String msg, Throwable tr) {
        return msg + '\n' + getStackTraceString(tr);
    }

    private static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        tr.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
