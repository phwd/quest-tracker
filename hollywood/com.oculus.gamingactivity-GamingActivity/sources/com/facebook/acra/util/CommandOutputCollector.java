package com.facebook.acra.util;

import java.io.IOException;
import java.io.InputStreamReader;

public class CommandOutputCollector {
    /* JADX INFO: finally extract failed */
    public static String collect(String... args) throws IOException {
        StringBuilder sb = new StringBuilder();
        Process p = new ProcessBuilder(args).redirectErrorStream(true).start();
        try {
            p.getOutputStream().close();
            InputStreamReader isr = new InputStreamReader(p.getInputStream());
            try {
                char[] buffer = new char[4096];
                while (true) {
                    int nrRead = isr.read(buffer, 0, buffer.length);
                    if (nrRead == -1) {
                        break;
                    }
                    sb.append(buffer, 0, nrRead);
                }
                isr.close();
                try {
                    p.waitFor();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                p.destroy();
                return sb.toString();
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        } catch (Throwable th2) {
            p.destroy();
            throw th2;
        }
    }
}
