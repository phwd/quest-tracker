package com.facebook.debug.log;

import javax.annotation.Nullable;

public class LoggingUtil {
    public static final String NO_HASHCODE = "null";

    public static String getHashCode(@Nullable Object object) {
        return getHashCode(object, NO_HASHCODE);
    }

    public static String getHashCode(@Nullable Object object, String defaultValue) {
        return object == null ? defaultValue : Integer.toHexString(object.hashCode());
    }

    public static String getCompactStackTrace(int deep) {
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        StringBuilder stringBuilder = new StringBuilder(stackTraceElements[1].getMethodName());
        int i = 2;
        while (i < deep && i < stackTraceElements.length) {
            StackTraceElement curr = stackTraceElements[i];
            StackTraceElement prev = stackTraceElements[i - 1];
            stringBuilder.append(" <- ");
            if (!curr.getClassName().equals(prev.getClassName())) {
                stringBuilder.append(curr.getClassName().substring(curr.getClassName().lastIndexOf(46) + 1));
                stringBuilder.append("#");
            }
            stringBuilder.append(curr.getMethodName());
            stringBuilder.append(":");
            stringBuilder.append(curr.getLineNumber());
            i++;
        }
        return stringBuilder.toString();
    }
}
