package com.facebook.acra;

import android.annotation.TargetApi;
import com.facebook.annotations.DoNotOptimize;
import java.util.Map;
import javax.annotation.Nullable;

@DoNotOptimize
public abstract class ExceptionTranslationHook {
    public volatile ExceptionTranslationHook next;

    @TargetApi(19)
    public static final class Api19Utils {
        public static final void addSuppressed(Throwable th, Throwable th2) {
        }
    }

    @Nullable
    public abstract Throwable translate(Throwable th, Map<String, String> map) throws Exception;

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
