package dalvik.system;

import java.util.function.Supplier;

public final class RuntimeHooks {
    private static Supplier zoneIdSupplier;

    public static Supplier getTimeZoneIdSupplier() {
        return zoneIdSupplier;
    }
}
