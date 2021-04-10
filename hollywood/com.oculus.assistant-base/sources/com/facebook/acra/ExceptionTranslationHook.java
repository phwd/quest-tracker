package com.facebook.acra;

import java.util.Map;

public abstract class ExceptionTranslationHook {
    public volatile ExceptionTranslationHook next;

    public final class Api19Utils {
        public static final void addSuppressed(Throwable th, Throwable th2) {
        }
    }

    public abstract Throwable translate(Throwable th, Map map);

    public static Throwable staplePreviousException(Throwable th, Throwable th2) {
        if (th != th2 && th.getCause() == null) {
            try {
                th.initCause(th2);
            } catch (IllegalArgumentException | IllegalStateException unused) {
            }
        }
        return th;
    }
}
