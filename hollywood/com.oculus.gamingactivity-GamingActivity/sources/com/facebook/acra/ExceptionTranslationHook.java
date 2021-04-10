package com.facebook.acra;

import android.annotation.TargetApi;
import android.os.Build;
import com.facebook.annotations.DoNotOptimize;
import java.util.Map;
import javax.annotation.Nullable;

@DoNotOptimize
public abstract class ExceptionTranslationHook {
    volatile ExceptionTranslationHook next;

    @Nullable
    public abstract Throwable translate(Throwable th, Map<String, String> map) throws Exception;

    public static Throwable staplePreviousException(Throwable to, Throwable previous) {
        boolean success = false;
        if (to != previous) {
            if (to.getCause() == null) {
                try {
                    to.initCause(previous);
                    success = true;
                } catch (IllegalArgumentException | IllegalStateException e) {
                }
            }
            if (!success && Build.VERSION.SDK_INT >= 19) {
                Api19Utils.addSuppressed(to, previous);
            }
        }
        return to;
    }

    @TargetApi(19)
    private static final class Api19Utils {
        private Api19Utils() {
        }

        static final void addSuppressed(Throwable to, Throwable suppressed) {
            to.addSuppressed(suppressed);
        }
    }
}
