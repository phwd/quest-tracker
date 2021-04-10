package com.facebook.acra.anr;

import android.os.Looper;
import com.facebook.infer.annotation.Nullsafe;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class StackTraceDumper {
    private static void printThread(PrintWriter printWriter, Thread thread, StackTraceElement[] stackTraceElementArr) {
        printWriter.print(thread);
        printWriter.print(" ");
        printWriter.print(thread.getState());
        printWriter.println(":");
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            printWriter.println(stackTraceElement);
        }
        printWriter.println();
    }

    public static void dumpStackTraces(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        Thread thread = Looper.getMainLooper().getThread();
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            printThread(printWriter, entry.getKey(), entry.getValue());
        }
        if (!allStackTraces.containsKey(thread)) {
            printThread(printWriter, thread, thread.getStackTrace());
        }
        printWriter.flush();
    }
}
