package java.util;

import java.security.AccessController;
import sun.util.logging.PlatformLogger;

/* access modifiers changed from: package-private */
public final class Tripwire {
    static final boolean ENABLED = ((Boolean) AccessController.doPrivileged($$Lambda$Tripwire$03Zb3zrd6SqpmwW72AFPa8slaw.INSTANCE)).booleanValue();
    private static final String TRIPWIRE_PROPERTY = "org.openjdk.java.util.stream.tripwire";

    private Tripwire() {
    }

    static void trip(Class<?> trippingClass, String msg) {
        PlatformLogger.getLogger(trippingClass.getName()).warning(msg, trippingClass.getName());
    }
}
