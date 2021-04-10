package com.bumptech.glide.util;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public final class Executors {
    private static final Executor DIRECT_EXECUTOR = new Executor() {
        /* class com.bumptech.glide.util.Executors.AnonymousClass2 */

        public void execute(@NonNull Runnable runnable) {
            runnable.run();
        }
    };
    private static final Executor MAIN_THREAD_EXECUTOR = new Executor() {
        /* class com.bumptech.glide.util.Executors.AnonymousClass1 */
        private final Handler handler = new Handler(Looper.getMainLooper());

        public void execute(@NonNull Runnable runnable) {
            this.handler.post(runnable);
        }
    };

    private Executors() {
    }

    public static Executor mainThreadExecutor() {
        return MAIN_THREAD_EXECUTOR;
    }

    public static Executor directExecutor() {
        return DIRECT_EXECUTOR;
    }

    @VisibleForTesting
    public static void shutdownAndAwaitTermination(ExecutorService executorService) {
        executorService.shutdownNow();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    throw new RuntimeException("Failed to shutdown");
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
