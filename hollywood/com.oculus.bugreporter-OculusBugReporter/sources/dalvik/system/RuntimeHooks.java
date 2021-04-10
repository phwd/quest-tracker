package dalvik.system;

import java.lang.Thread;
import java.util.Objects;
import java.util.TimeZone;
import java.util.function.Supplier;

public final class RuntimeHooks {
    private static Supplier<String> zoneIdSupplier;

    private RuntimeHooks() {
    }

    public static void setTimeZoneIdSupplier(Supplier<String> zoneIdSupplier2) {
        if (zoneIdSupplier == null) {
            zoneIdSupplier = (Supplier) Objects.requireNonNull(zoneIdSupplier2);
            TimeZone.setDefault(null);
            return;
        }
        throw new UnsupportedOperationException("zoneIdSupplier instance already set");
    }

    public static Supplier<String> getTimeZoneIdSupplier() {
        return zoneIdSupplier;
    }

    public static void setUncaughtExceptionPreHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        Thread.setUncaughtExceptionPreHandler(uncaughtExceptionHandler);
    }
}
