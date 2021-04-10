package com.facebook.internal;

import X.AnonymousClass006;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    public static final String LOG_TAG_BASE = "FacebookSDK.";
    public static final HashMap<String, String> stringsToReplace = new HashMap<>();
    public final LoggingBehavior behavior;
    public StringBuilder contents;
    public int priority = 3;
    public final String tag;

    public void appendKeyValue(String str, Object obj) {
        append("  %s:\t%s\n", str, obj);
    }

    public static synchronized void registerAccessToken(String str) {
        synchronized (Logger.class) {
            if (!FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
                registerStringToReplace(str, "ACCESS_TOKEN_REMOVED");
            }
        }
    }

    public static synchronized void registerStringToReplace(String str, String str2) {
        synchronized (Logger.class) {
            stringsToReplace.put(str, str2);
        }
    }

    public static synchronized String replaceStrings(String str) {
        synchronized (Logger.class) {
            for (Map.Entry<String, String> entry : stringsToReplace.entrySet()) {
                str = str.replace(entry.getKey(), entry.getValue());
            }
        }
        return str;
    }

    private boolean shouldLog() {
        return FacebookSdk.isLoggingBehaviorEnabled(this.behavior);
    }

    public String getContents() {
        return replaceStrings(this.contents.toString());
    }

    public void logString(String str) {
        log(this.behavior, this.priority, this.tag, str);
    }

    public Logger(LoggingBehavior loggingBehavior, String str) {
        Validate.notNullOrEmpty(str, "tag");
        this.behavior = loggingBehavior;
        this.tag = AnonymousClass006.A05(LOG_TAG_BASE, str);
        this.contents = new StringBuilder();
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        Validate.oneOf(Integer.valueOf(i), "value", 7, 3, 6, 4, 2, 5);
        this.priority = i;
    }

    public static void log(LoggingBehavior loggingBehavior, int i, String str, String str2) {
        if (FacebookSdk.isLoggingBehaviorEnabled(loggingBehavior)) {
            String replaceStrings = replaceStrings(str2);
            if (!str.startsWith(LOG_TAG_BASE)) {
                str = AnonymousClass006.A05(LOG_TAG_BASE, str);
            }
            Log.println(i, str, replaceStrings);
            if (loggingBehavior == LoggingBehavior.DEVELOPER_ERRORS) {
                new Exception().printStackTrace();
            }
        }
    }

    public static void log(LoggingBehavior loggingBehavior, int i, String str, String str2, Object... objArr) {
        if (FacebookSdk.isLoggingBehaviorEnabled(loggingBehavior)) {
            log(loggingBehavior, i, str, String.format(str2, objArr));
        }
    }

    public static void log(LoggingBehavior loggingBehavior, String str, String str2) {
        log(loggingBehavior, 3, str, str2);
    }

    public static void log(LoggingBehavior loggingBehavior, String str, String str2, Object... objArr) {
        if (FacebookSdk.isLoggingBehaviorEnabled(loggingBehavior)) {
            log(loggingBehavior, 3, str, String.format(str2, objArr));
        }
    }

    public void append(String str) {
        if (FacebookSdk.isLoggingBehaviorEnabled(this.behavior)) {
            this.contents.append(str);
        }
    }

    public void append(String str, Object... objArr) {
        if (FacebookSdk.isLoggingBehaviorEnabled(this.behavior)) {
            this.contents.append(String.format(str, objArr));
        }
    }

    public void append(StringBuilder sb) {
        if (FacebookSdk.isLoggingBehaviorEnabled(this.behavior)) {
            this.contents.append((CharSequence) sb);
        }
    }

    public void log() {
        logString(this.contents.toString());
        this.contents = new StringBuilder();
    }
}
