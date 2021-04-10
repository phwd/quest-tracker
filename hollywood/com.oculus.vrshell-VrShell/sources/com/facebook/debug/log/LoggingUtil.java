package com.facebook.debug.log;

import javax.annotation.Nullable;

public class LoggingUtil {
    public static final String NO_HASHCODE = "null";

    public static String getHashCode(@Nullable Object obj) {
        return getHashCode(obj, NO_HASHCODE);
    }

    public static String getHashCode(@Nullable Object obj, String str) {
        return obj == null ? str : Integer.toHexString(obj.hashCode());
    }

    public static String getCompactStackTrace(int i) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        StringBuilder sb = new StringBuilder(stackTrace[1].getMethodName());
        int i2 = 2;
        while (i2 < i && i2 < stackTrace.length) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            StackTraceElement stackTraceElement2 = stackTrace[i2 - 1];
            sb.append(" <- ");
            if (!stackTraceElement.getClassName().equals(stackTraceElement2.getClassName())) {
                sb.append(stackTraceElement.getClassName().substring(stackTraceElement.getClassName().lastIndexOf(46) + 1));
                sb.append("#");
            }
            sb.append(stackTraceElement.getMethodName());
            sb.append(":");
            sb.append(stackTraceElement.getLineNumber());
            i2++;
        }
        return sb.toString();
    }
}
