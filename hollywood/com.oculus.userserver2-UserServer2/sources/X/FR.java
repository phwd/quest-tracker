package X;

public final class FR extends RuntimeException {
    public FR(Exception exc) {
        super("An exception was thrown by an Executor", exc);
    }
}
