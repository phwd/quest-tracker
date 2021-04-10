package com.facebook.acra.util;

import java.io.IOException;
import java.io.InputStreamReader;

public class CommandOutputCollector {
    /* JADX INFO: finally extract failed */
    public static String collect(String... strArr) throws IOException {
        StringBuilder sb = new StringBuilder();
        Process start = new ProcessBuilder(strArr).redirectErrorStream(true).start();
        try {
            start.getOutputStream().close();
            InputStreamReader inputStreamReader = new InputStreamReader(start.getInputStream());
            try {
                char[] cArr = new char[4096];
                while (true) {
                    int read = inputStreamReader.read(cArr, 0, 4096);
                    if (read == -1) {
                        break;
                    }
                    sb.append(cArr, 0, read);
                }
                inputStreamReader.close();
                try {
                    start.waitFor();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
                start.destroy();
                return sb.toString();
            } catch (Throwable unused2) {
            }
            throw th;
        } catch (Throwable th) {
            start.destroy();
            throw th;
        }
    }
}
