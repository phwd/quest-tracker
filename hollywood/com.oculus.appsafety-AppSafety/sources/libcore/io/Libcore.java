package libcore.io;

import java.util.Objects;

public final class Libcore {
    public static volatile Os os = new BlockGuardOs(rawOs);
    public static final Os rawOs = new Linux();

    private Libcore() {
    }

    public static Os getOs() {
        return os;
    }

    public static boolean compareAndSetOs(Os expect, Os update) {
        Objects.requireNonNull(update);
        boolean result = false;
        if (os != expect) {
            return false;
        }
        synchronized (Libcore.class) {
            if (os == expect) {
                result = true;
            }
            if (result) {
                os = update;
            }
        }
        return result;
    }
}
