package java.util.logging;

public abstract class Handler {
    private static final int offValue = Level.OFF.intValue();
    private volatile ErrorManager errorManager = new ErrorManager();
    private volatile Level logLevel = Level.ALL;
    private final LogManager manager = LogManager.getLogManager();
    boolean sealed = true;

    public abstract void close();

    public abstract void publish(LogRecord logRecord);

    protected Handler() {
    }

    public synchronized void setLevel(Level level) {
        if (level != null) {
            checkPermission();
            this.logLevel = level;
        } else {
            throw new NullPointerException();
        }
    }

    /* access modifiers changed from: package-private */
    public void checkPermission() {
        if (this.sealed) {
            this.manager.checkPermission();
        }
    }
}
