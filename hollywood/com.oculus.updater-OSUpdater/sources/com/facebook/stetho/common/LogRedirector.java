package com.facebook.stetho.common;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LogRedirector {
    private static volatile Logger sLogger;

    public interface Logger {
        void log(int i, String str, String str2);
    }

    public static void e(String str, String str2, Throwable th) {
        e(str, str2 + "\n" + formatThrowable(th));
    }

    public static void e(String str, String str2) {
        log(6, str, str2);
    }

    public static void d(String str, String str2) {
        log(3, str, str2);
    }

    private static String formatThrowable(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace();
        printWriter.flush();
        return stringWriter.toString();
    }

    private static void log(int i, String str, String str2) {
        Logger logger = sLogger;
        if (logger != null) {
            logger.log(i, str, str2);
        } else {
            Log.println(i, str, str2);
        }
    }
}
