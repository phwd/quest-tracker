package libraries.debug.log;

public final class LogPrefixer {
    public static final String renderClass(Class<?> cls) {
        return cls.getSimpleName();
    }
}
