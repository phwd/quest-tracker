package com.google.common.util.concurrent;

import com.google.common.annotations.VisibleForTesting;
import java.lang.Thread;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class UncaughtExceptionHandlers {
    private UncaughtExceptionHandlers() {
    }

    public static Thread.UncaughtExceptionHandler systemExit() {
        return new Exiter(Runtime.getRuntime());
    }

    @VisibleForTesting
    static final class Exiter implements Thread.UncaughtExceptionHandler {
        private static final Logger logger = Logger.getLogger(Exiter.class.getName());
        private final Runtime runtime;

        Exiter(Runtime runtime2) {
            this.runtime = runtime2;
        }

        public void uncaughtException(Thread t, Throwable e) {
            try {
                logger.log(Level.SEVERE, String.format("Caught an exception in %s.  Shutting down.", t), e);
            } catch (Throwable th) {
                this.runtime.exit(1);
                throw th;
            }
            this.runtime.exit(1);
        }
    }
}
