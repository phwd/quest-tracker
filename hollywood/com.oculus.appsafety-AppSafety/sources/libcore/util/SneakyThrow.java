package libcore.util;

public class SneakyThrow {
    private SneakyThrow() {
    }

    public static void sneakyThrow(Throwable t) {
        sneakyThrow_(t);
    }

    private static <T extends Throwable> void sneakyThrow_(Throwable t) throws Throwable {
        throw t;
    }
}
