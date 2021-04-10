package com.oculus.vrshell.home;

public class ThrowableUtil {
    public static String getStackTrace(Throwable e) {
        StringBuilder stackTrace = new StringBuilder();
        recursiveStackTrace(e, stackTrace);
        return stackTrace.toString();
    }

    private static void recursiveStackTrace(Throwable e, StringBuilder stackTrace) {
        stackTrace.append(e.getClass().getName());
        stackTrace.append(": ");
        stackTrace.append(e.getMessage());
        stackTrace.append(".\n");
        throwableStackTrace(e, stackTrace);
        if (e.getCause() != null) {
            stackTrace.append("Caused by: ");
            recursiveStackTrace(e.getCause(), stackTrace);
        }
    }

    private static void throwableStackTrace(Throwable e, StringBuilder stackTrace) {
        StackTraceElement[] stack;
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            stackTrace.append("        at ");
            stackTrace.append(stackTraceElement.toString());
            stackTrace.append('\n');
        }
    }
}
