package com.facebook.acra.anr;

import android.os.Looper;
import com.facebook.infer.annotation.Nullsafe;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class StackTraceDumper {
    public static void printThread(PrintWriter printWriter, Thread thread, StackTraceElement[] stackTraceElementArr) {
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
        dumpStackTraces(outputStream, null, null);
    }

    public static void dumpStackTraces(OutputStream outputStream, @Nullable String str, @Nullable String str2) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        if (str != null) {
            printWriter.println(str);
            printWriter.println(str2);
        }
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
