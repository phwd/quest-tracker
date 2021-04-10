package com.oculus.os;

import android.content.Context;
import java.io.File;

public class LogCollectorClient {

    public static class CollectorResult {
        CollectorResult() {
            throw new RuntimeException("Stub!");
        }

        public boolean isError() {
            throw new RuntimeException("Stub!");
        }
    }

    public LogCollectorClient() {
        throw new RuntimeException("Stub!");
    }

    public synchronized CollectorResult collectLogArchive(Context context, File file) {
        throw new RuntimeException("Stub!");
    }

    public synchronized CollectorResult collectScreenshot(Context context, File file) {
        throw new RuntimeException("Stub!");
    }
}
