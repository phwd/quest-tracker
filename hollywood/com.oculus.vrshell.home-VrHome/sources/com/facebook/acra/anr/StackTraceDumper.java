package com.facebook.acra.anr;

import android.os.Looper;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import javax.annotation.Nullable;

public class StackTraceDumper {
    public static void dumpStackTraces(OutputStream output) {
        dumpStackTraces(output, null, null);
    }

    public static void dumpStackTraces(OutputStream output, @Nullable String versionCode, @Nullable String versionName) {
        PrintWriter writer = new PrintWriter(output);
        if (versionCode != null) {
            writer.println(versionCode);
            writer.println(versionName);
        }
        Map<Thread, StackTraceElement[]> threadStackTraces = Thread.getAllStackTraces();
        Thread mainThread = Looper.getMainLooper().getThread();
        for (Map.Entry<Thread, StackTraceElement[]> threadStackTrace : threadStackTraces.entrySet()) {
            printThread(writer, threadStackTrace.getKey(), threadStackTrace.getValue());
        }
        if (!threadStackTraces.containsKey(mainThread)) {
            printThread(writer, mainThread, mainThread.getStackTrace());
        }
        writer.flush();
    }

    private static void printThread(PrintWriter writer, Thread thread, StackTraceElement[] stackTrace) {
        writer.print(thread);
        writer.print(" ");
        writer.print(thread.getState());
        writer.println(":");
        for (StackTraceElement element : stackTrace) {
            writer.println(element);
        }
        writer.println();
    }
}
