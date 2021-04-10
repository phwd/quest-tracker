package com.facebook.acra;

import android.annotation.TargetApi;
import android.os.Build;
import com.facebook.annotations.DoNotOptimize;
import com.facebook.ultralight.UL;
import java.util.Map;
import javax.annotation.Nullable;

@DoNotOptimize
public abstract class ExceptionTranslationHook {
    volatile ExceptionTranslationHook next;

    @Nullable
    public abstract Throwable translate(Throwable th, Map<String, String> map) throws Exception;

    public static Throwable staplePreviousException(Throwable th, Throwable th2) {
        boolean z;
        if (th != th2) {
            if (th.getCause() == null) {
                try {
                    th.initCause(th2);
                    z = true;
                } catch (IllegalArgumentException | IllegalStateException unused) {
                }
                if (!z && Build.VERSION.SDK_INT >= 19) {
                    Api19Utils.addSuppressed(th, th2);
                }
            }
            z = false;
            Api19Utils.addSuppressed(th, th2);
        }
        return th;
    }

    @TargetApi(UL.id._UL__ULSEP_com_oculus_updater_core_monitors_SystemUpdatePolicyMonitor_ULSEP_BINDING_ID)
    private static final class Api19Utils {
        static final void addSuppressed(Throwable th, Throwable th2) {
        }

        private Api19Utils() {
        }
    }
}
