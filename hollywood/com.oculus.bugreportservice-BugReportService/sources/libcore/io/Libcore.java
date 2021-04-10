package libcore.io;

public final class Libcore {
    public static volatile Os os = new BlockGuardOs(rawOs);
    public static final Os rawOs = new Linux();
}
